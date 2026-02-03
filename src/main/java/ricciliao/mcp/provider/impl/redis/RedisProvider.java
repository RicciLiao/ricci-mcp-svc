package ricciliao.mcp.provider.impl.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.search.Query;
import redis.clients.jedis.search.SearchResult;
import ricciliao.mcp.common.McpConstants;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.x.log.api.XLogger;
import ricciliao.x.log.api.XLoggerFactory;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpCriteria;
import ricciliao.x.mcp.query.McpQuery;

import java.lang.reflect.Field;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RedisProvider extends AbstractMcpProvider {

    private static final XLogger logger = XLoggerFactory.getLogger(RedisProvider.class);
    private static final String OP_FAILED = "Cannot operate cache for {}";

    private final JedisPooled jedisPooled;
    private final ObjectMapper objectMapper;
    private final String upsertScript;
    private final String indexName;

    public RedisProvider(@Nonnull McpProviderInfoPo po,
                         @Nonnull JedisPooled jedisPooled,
                         @Nonnull ObjectMapper objectMapper,
                         @Nonnull String upsertScript) {
        super(po);
        this.jedisPooled = jedisPooled;
        this.upsertScript = this.jedisPooled.scriptLoad(upsertScript);
        this.objectMapper = objectMapper;
        this.indexName = super.getIdentifier() + "_index";
    }

    @Override
    public boolean create(AbstractProviderCacheMessage.Single single) {
        try {

            return 1L ==
                    Long.parseLong(
                            this.jedisPooled.evalsha(
                                    this.upsertScript,
                                    Collections.singletonList(this.buildRedisKey(single.getData().getUid())),
                                    Arrays.asList(
                                            this.objectMapper.writeValueAsString(single.getData()),
                                            String.valueOf(super.getTtlSeconds().getSeconds()),
                                            String.valueOf(1)
                                    )
                            ).toString()
                    );
        } catch (Exception e) {
            logger.error(OP_FAILED, super.getIdentifier(), e);

            return false;
        }
    }

    @Override
    public boolean update(AbstractProviderCacheMessage.Single single) {
        try {

            return 1L ==
                    Long.parseLong(
                            this.jedisPooled.evalsha(
                                    this.upsertScript,
                                    Collections.singletonList(this.buildRedisKey(single.getData().getUid())),
                                    Arrays.asList(
                                            this.objectMapper.writeValueAsString(single.getData()),
                                            String.valueOf(super.getTtlSeconds().getSeconds()),
                                            String.valueOf(0)
                                    )
                            ).toString()
                    );
        } catch (Exception e) {
            logger.error(OP_FAILED, super.getIdentifier(), e);

            return false;
        }
    }

    @Nonnull
    @Override
    public AbstractProviderCacheMessage.Single get(String key) {

        return AbstractProviderCacheMessage.of(
                this.objectMapper.convertValue(
                        this.jedisPooled.jsonGet(this.buildRedisKey(key)),
                        ProviderCache.class
                )
        );
    }

    @Override
    public boolean delete(String key) {

        return this.jedisPooled.del(this.buildRedisKey(key)) == 1L;
    }

    @Override
    public boolean create(AbstractProviderCacheMessage.Batch batch) {
        List<Response<Object>> responseList = new ArrayList<>();
        try (Pipeline pipeline = this.jedisPooled.pipelined()) {
            for (ProviderCache datum : batch.getData()) {
                responseList.add(
                        pipeline.evalsha(
                                this.upsertScript,
                                Collections.singletonList(this.buildRedisKey(datum.getUid())),
                                Arrays.asList(
                                        this.objectMapper.writeValueAsString(datum),
                                        String.valueOf(super.getTtlSeconds().getSeconds()),
                                        String.valueOf(1)
                                )
                        )
                );
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.error(OP_FAILED, super.getIdentifier(), e);

            return false;
        }

        return batch.getData().length ==
                responseList.stream().mapToLong(r -> Long.parseLong(r.get().toString())).sum();
    }

    @Override
    public boolean update(AbstractProviderCacheMessage.Batch batch) {
        List<Response<Object>> responseList = new ArrayList<>();
        try (Pipeline pipeline = this.jedisPooled.pipelined()) {
            for (ProviderCache datum : batch.getData()) {
                responseList.add(
                        pipeline.evalsha(
                                this.upsertScript,
                                Collections.singletonList(this.buildRedisKey(datum.getUid())),
                                Arrays.asList(
                                        this.objectMapper.writeValueAsString(datum),
                                        String.valueOf(super.getTtlSeconds().getSeconds()),
                                        String.valueOf(0)
                                )
                        )
                );
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.error(OP_FAILED, super.getIdentifier(), e);

            return false;
        }

        return batch.getData().length ==
                responseList.stream().mapToLong(r -> Long.parseLong(r.get().toString())).sum();
    }

    @Nonnull
    @Override
    public AbstractProviderCacheMessage.Batch list(McpQuery query) {
        SearchResult sr = this.jedisPooled.ftSearch(this.indexName, this.toQuery(query));
        if (sr.getTotalResults() > 0) {
            ProviderCache[] stores = new ProviderCache[Math.toIntExact(sr.getTotalResults())];
            try {
                for (int i = 0; i < sr.getDocuments().size(); i++) {
                    stores[i] =
                            this.objectMapper.readValue(
                                    sr.getDocuments().get(i).getString("$"),
                                    ProviderCache.class
                            );
                }
            } catch (JsonProcessingException e) {
                logger.error(OP_FAILED, this.getIdentifier(), e);
            }

            return AbstractProviderCacheMessage.of(stores);
        }

        return AbstractProviderCacheMessage.of(new ProviderCache[0]);
    }

    @Override
    public boolean delete(McpQuery query) {
        boolean finish = false;
        while (!finish) {
            AbstractProviderCacheMessage.Batch batch = this.list(query);
            if (ArrayUtils.isNotEmpty(batch.getData())) {
                this.jedisPooled.del(
                        Arrays.stream(batch.getData())
                                .map(dto -> this.buildRedisKey(dto.getUid()))
                                .toArray(String[]::new)
                );
            } else {
                finish = true;
            }
        }

        return true;
    }

    @Override
    public McpProviderInfo info() {
        McpProviderInfo result = new McpProviderInfo(this.getIdentifier());
        long count = this.jedisPooled
                .ftSearch(
                        this.indexName,
                        new Query("*").limit(0, 0)
                )
                .getTotalResults();
        result.setCount(count);
        if (count > 0) {
            McpQuery query = new McpQuery();
            query.setSortBy(McpCriteria.Property.UPDATED_DTM);
            query.setSortDirection(McpCriteria.Sort.Direction.DESC);
            query.setLimit(1L);
            AbstractProviderCacheMessage.Batch maxUpdated = this.list(query);

            query.setSortBy(McpCriteria.Property.CREATED_DTM);
            query.setSortDirection(McpCriteria.Sort.Direction.ASC);
            query.setLimit(1L);
            AbstractProviderCacheMessage.Batch minCreated = this.list(query);

            result.setCreatedDtm(minCreated.getData()[0].getCreatedDtm());
            result.setMaxUpdatedDtm(maxUpdated.getData()[0].getUpdatedDtm());
        }

        return result;
    }

    @Override
    protected void destroy() {
        this.jedisPooled.close();
    }

    private Query toQuery(McpQuery query) {
        Query searchQ;
        if (MapUtils.isNotEmpty(query.getCriteriaMap())) {
            StringBuilder sbr = new StringBuilder();
            query.getCriteriaMap().entrySet()
                    .stream()
                    .filter(es -> Objects.nonNull(super.getPropertyField(es.getKey())))
                    .forEach(es -> {
                        Field field = super.getPropertyField(es.getKey());
                        if (Temporal.class.isAssignableFrom(field.getType())) {
                            sbr.append(String.format(" @%s: [%s %s] ", field.getName(), es.getValue(), es.getValue()));
                        } else if (String.class.isAssignableFrom(field.getType())) {
                            sbr.append(String.format(" @%s: %s ", field.getName(), es.getValue()));
                        }
                    });
            searchQ = new Query(sbr.toString());
        } else {
            searchQ = new Query();
        }
        if (Objects.nonNull(query.getLimit()) && query.getLimit() != 0L) {
            searchQ = searchQ.limit(0, query.getLimit().intValue());
        } else {
            searchQ = searchQ.limit(0, McpConstants.DEFAULT_CACHE_OP_BATCH_LIMIT);
        }
        if (Objects.nonNull(query.getSortBy())
                && Objects.nonNull(query.getSortDirection())
                && Objects.nonNull(super.getPropertyFieldName(query.getSortBy()))) {
            searchQ.setSortBy(
                    this.getPropertyFieldName(query.getSortBy()),
                    McpCriteria.Sort.Direction.ASC.equals(query.getSortDirection())
            );
        }

        return searchQ;
    }

    private String buildRedisKey(String id) {

        return String.format(
                "%s:%s:%s",
                super.getIdentifier().getConsumer(),
                super.getIdentifier().getStore(),
                id.replace("_", ":")
        );
    }

}
