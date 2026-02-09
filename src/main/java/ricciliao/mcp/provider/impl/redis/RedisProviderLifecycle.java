package ricciliao.mcp.provider.impl.redis;

import jakarta.annotation.Nonnull;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.search.IndexDefinition;
import redis.clients.jedis.search.IndexOptions;
import redis.clients.jedis.search.Schema;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderLifecycle;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.mcp.query.McpCriteria;

public class RedisProviderLifecycle extends AbstractMcpProviderLifecycle {

    private final JedisPooled authJedisPooled;
    private final JedisPool authJedisPool;

    public RedisProviderLifecycle(@Nonnull McpProviderRegistry registry,
                                  @Nonnull JedisPooled authJedisPooled,
                                  @Nonnull JedisPool authJedisPool) {
        super(registry);
        this.authJedisPooled = authJedisPooled;
        this.authJedisPool = authJedisPool;
    }

    @Override
    protected void preCreation(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) {
        try (Jedis jedis = authJedisPool.getResource()) {
            if (!jedis.aclUsers().contains(info.getConsumer())) {
                jedis.aclSetUser(
                        info.getConsumer(),
                        "on",
                        ">" + passInfo.getPassKey(),
                        "~" + RedisHelper.keyPrefix(info.getConsumer(), info.getStore()) + "*",
                        "+@all",
                        "-@admin"
                );
            }
        }
    }

    @Override
    protected void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info) {
        String indexName = RedisHelper.indexName(provider.getIdentifier());
        String prefix = RedisHelper.keyPrefix(provider.getIdentifier());
        if (
                this.authJedisPooled
                        .ftList()
                        .stream()
                        .filter(index -> index.equalsIgnoreCase(indexName))
                        .findAny()
                        .isEmpty()
        ) {
            Schema.Field id = new Schema.Field("$." + provider.getPropertyFieldName(McpCriteria.Property.ID), Schema.FieldType.TEXT, true);
            id.as(provider.getPropertyFieldName(McpCriteria.Property.ID));
            Schema.Field createdDtm = new Schema.Field("$" + provider.getPropertyFieldName(McpCriteria.Property.CREATED_DTM), Schema.FieldType.NUMERIC, true);
            createdDtm.as(provider.getPropertyFieldName(McpCriteria.Property.CREATED_DTM));
            Schema.Field updatedDtm = new Schema.Field("$" + provider.getPropertyFieldName(McpCriteria.Property.UPDATED_DTM), Schema.FieldType.NUMERIC, true);
            updatedDtm.as(provider.getPropertyFieldName(McpCriteria.Property.UPDATED_DTM));
            this.authJedisPooled.ftCreate(
                    indexName,
                    IndexOptions
                            .defaultOptions()
                            .setDefinition(
                                    new IndexDefinition(IndexDefinition.Type.JSON)
                                            .setPrefixes(prefix)
                            ),
                    Schema.from(id, createdDtm, updatedDtm)
            );
        }
    }

    @Override
    protected void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info) {
        //do nothing
    }

    @Override
    protected void postDestruction(@Nonnull McpProviderInfoPo info) {
        //do nothing
    }
}
