package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.ProviderCacheMessage;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpQuery;

public interface McpProviderRepository {

    boolean create(ProviderCacheMessage.Single single);

    boolean update(ProviderCacheMessage.Single single);

    @Nonnull
    ProviderCacheMessage.Single get(String id);

    boolean delete(String id);

    boolean create(ProviderCacheMessage.Batch batch);

    boolean update(ProviderCacheMessage.Batch batch);

    @Nonnull
    ProviderCacheMessage.Batch list(McpQuery query);

    boolean delete(McpQuery query);

    McpProviderInfo info();

}
