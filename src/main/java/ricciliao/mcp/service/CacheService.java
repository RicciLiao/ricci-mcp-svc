package ricciliao.mcp.service;


import ricciliao.mcp.pojo.ProviderCacheMessage;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpQuery;

public interface CacheService {

    String create(McpIdentifier identifier, ProviderCacheMessage.Single single);

    boolean update(McpIdentifier identifier, ProviderCacheMessage.Single single);

    ProviderCacheMessage.Single get(McpIdentifier identifier, String id);

    boolean delete(McpIdentifier identifier, String id);

    boolean create(McpIdentifier identifier, ProviderCacheMessage.Batch batch);

    boolean update(McpIdentifier identifier, ProviderCacheMessage.Batch batch);

    ProviderCacheMessage.Batch list(McpIdentifier identifier, McpQuery query);

    boolean delete(McpIdentifier identifier, McpQuery query);

    McpProviderInfo info(McpIdentifier identifier);

}
