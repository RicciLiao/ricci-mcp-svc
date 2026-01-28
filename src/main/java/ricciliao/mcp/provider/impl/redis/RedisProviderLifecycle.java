package ricciliao.mcp.provider.impl.redis;

import jakarta.annotation.Nonnull;
import org.springframework.lang.NonNull;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.search.IndexDefinition;
import redis.clients.jedis.search.IndexOptions;
import redis.clients.jedis.search.Schema;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderLifecycle;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.mcp.query.McpCriteria;

public class RedisProviderLifecycle extends AbstractMcpProviderLifecycle {

    private final JedisPool authJedisPool;
    private final JedisPooled authJedisPooled;

    public RedisProviderLifecycle(@NonNull McpProviderRegistry registry,
                                  @Nonnull JedisPool authJedisPool,
                                  @Nonnull JedisPooled authJedisPooled) {
        super(registry);
        this.authJedisPool = authJedisPool;
        this.authJedisPooled = authJedisPooled;
    }

    @Override
    protected void preCreation(@NonNull McpProviderInfoPo po) {
        //do nothing
    }

    @Override
    protected void postCreation(@NonNull AbstractMcpProvider provider, @NonNull McpProviderInfoPo po) {
        String indexName = provider.getIdentifier() + "_index";
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
                                            .setPrefixes(provider.getIdentifier().toString())),
                    Schema.from(id, createdDtm, updatedDtm)
            );
        }
    }

    @Override
    protected void preDestruction(@NonNull AbstractMcpProvider provider, @NonNull McpProviderInfoPo po) {
        //do nothing
    }

    @Override
    protected void postDestruction(@NonNull McpProviderInfoPo po) {
        //do nothing
    }
}
