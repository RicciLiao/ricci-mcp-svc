package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpQuery;

public interface McpProviderRepository {

    boolean create(AbstractProviderCacheMessage.Single single);

    boolean update(AbstractProviderCacheMessage.Single single);

    @Nonnull
    AbstractProviderCacheMessage.Single get(String id);

    boolean delete(String id);

    boolean create(AbstractProviderCacheMessage.Batch batch);

    boolean update(AbstractProviderCacheMessage.Batch batch);

    @Nonnull
    AbstractProviderCacheMessage.Batch list(McpQuery query);

    boolean delete(McpQuery query);

    McpProviderInfo info();

}
