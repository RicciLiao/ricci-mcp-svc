package ricciliao.cache.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.Nonnull;
import ricciliao.cache.pojo.ProviderCache;
import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.io.Serializable;
import java.util.Arrays;

public class ProviderOpBatchConverter extends CacheOperationConverter<ProviderOperation.Batch> {

    public ProviderOpBatchConverter(ObjectMapper objectMapper,
                                    CacheProviderSelector cacheProviderSelector) {
        super(objectMapper, cacheProviderSelector);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return ProviderOperation.Batch.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    ProviderOperation.Batch readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws JsonProcessingException {
        ArrayNode arrayNode = (ArrayNode) node;
        ProviderCache[] stores = new ProviderCache[arrayNode.size()];
        for (int i = 0; i < arrayNode.size(); i++) {
            stores[i] = objectMapper.convertValue(this.encode(arrayNode.get(i), cacheProvider), new TypeReference<>() {
            });
        }

        return ProviderOperation.of(stores);
    }

    @Nonnull
    @Override
    ResponseData writeInternal(@Nonnull Serializable data) {

        return SimpleData.of(
                Arrays.stream((ProviderCache[]) data)
                        .map(store -> SneakyThrowUtils.get(() -> this.decode(store))).toList()
        );
    }

}
