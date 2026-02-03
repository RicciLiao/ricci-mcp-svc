package ricciliao.mcp.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.io.Serializable;
import java.util.Arrays;

public class McpCacheBatchConverter extends AbstractMcpCacheMessageConverter<AbstractProviderCacheMessage.Batch> {

    public McpCacheBatchConverter(ObjectMapper objectMapper,
                                  McpProviderRegistry mcpProviderRegistry) {
        super(objectMapper, mcpProviderRegistry);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return AbstractProviderCacheMessage.Batch.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    AbstractProviderCacheMessage.Batch readInternal(@Nonnull JsonNode node, AbstractMcpProvider cacheProvider) throws JsonProcessingException {
        ArrayNode arrayNode = (ArrayNode) node.get("data");
        ProviderCache[] stores = new ProviderCache[arrayNode.size()];
        for (int i = 0; i < arrayNode.size(); i++) {
            stores[i] = objectMapper.convertValue(this.encode(arrayNode.get(i), cacheProvider), new TypeReference<>() {
            });
        }

        return AbstractProviderCacheMessage.of(stores);
    }

    @Nonnull
    @Override
    PayloadData writeInternal(@Nonnull Serializable data) {

        return SimplePayloadData.of(
                Arrays.stream((ProviderCache[]) data)
                        .map(store -> SneakyThrowUtils.get(() -> this.decode(store))).toList()
        );
    }

}
