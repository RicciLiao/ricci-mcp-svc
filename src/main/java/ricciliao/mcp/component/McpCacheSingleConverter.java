package ricciliao.mcp.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.component.payload.PayloadData;

import java.io.IOException;
import java.io.Serializable;

public class McpCacheSingleConverter extends AbstractMcpCacheMessageConverter<AbstractProviderCacheMessage.Single> {

    public McpCacheSingleConverter(ObjectMapper objectMapper,
                                   McpProviderRegistry mcpProviderRegistry) {
        super(objectMapper, mcpProviderRegistry);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return AbstractProviderCacheMessage.Single.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    AbstractProviderCacheMessage.Single readInternal(@Nonnull JsonNode node, AbstractMcpProvider cacheProvider) throws JsonProcessingException {

        return AbstractProviderCacheMessage.of(this.encode(node, cacheProvider));
    }

    @Nonnull
    @Override
    PayloadData writeInternal(@Nonnull Serializable data) throws IOException {

        return this.decode((ProviderCache) data);
    }

}
