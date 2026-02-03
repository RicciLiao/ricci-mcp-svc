package ricciliao.mcp.service;


import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpQuery;

public interface CacheService {

    String create(McpIdentifier identifier, AbstractProviderCacheMessage.Single single);

    boolean update(McpIdentifier identifier, AbstractProviderCacheMessage.Single single);

    AbstractProviderCacheMessage.Single get(McpIdentifier identifier, String id);

    boolean delete(McpIdentifier identifier, String id);

    boolean create(McpIdentifier identifier, AbstractProviderCacheMessage.Batch batch);

    boolean update(McpIdentifier identifier, AbstractProviderCacheMessage.Batch batch);

    AbstractProviderCacheMessage.Batch list(McpIdentifier identifier, McpQuery query);

    boolean delete(McpIdentifier identifier, McpQuery query);

    McpProviderInfo info(McpIdentifier identifier);

}
