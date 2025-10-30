package ricciliao.cache.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import ricciliao.cache.pojo.ProviderCache;
import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.x.component.payload.PayloadData;

import java.io.IOException;
import java.io.Serializable;

public class ProviderOpSingleConverter extends CacheOperationConverter<ProviderOperation.Single> {

    public ProviderOpSingleConverter(ObjectMapper objectMapper,
                                     CacheProviderSelector cacheProvider) {
        super(objectMapper, cacheProvider);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return ProviderOperation.Single.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    ProviderOperation.Single readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws JsonProcessingException {

        return ProviderOperation.of(this.encode(node, cacheProvider));
    }

    @Nonnull
    @Override
    PayloadData writeInternal(@Nonnull Serializable data) throws IOException {

        return this.decode((ProviderCache) data);
    }

}
