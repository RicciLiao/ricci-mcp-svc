package ricciliao.cache.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import ricciliao.cache.pojo.ProviderCache;
import ricciliao.x.cache.XCacheConstants;
import ricciliao.x.cache.pojo.AbstractCacheOperation;
import ricciliao.x.cache.pojo.ConsumerIdentifier;
import ricciliao.x.cache.pojo.StoreCache;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.response.Response;
import ricciliao.x.component.response.ResponseHttpMessageConverter;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.response.data.ResponseData;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public abstract class CacheOperationConverter<T extends AbstractCacheOperation<? extends Serializable>> extends AbstractHttpMessageConverter<T> {

    private final CacheProviderSelector cacheProviderSelector;
    private final ResponseHttpMessageConverter responseHttpMessageConverter;
    protected final ObjectMapper objectMapper;

    protected CacheOperationConverter(ObjectMapper objectMapper, CacheProviderSelector cacheProviderSelector) {
        super(MediaType.APPLICATION_JSON);
        this.objectMapper = objectMapper;
        this.cacheProviderSelector = cacheProviderSelector;
        this.responseHttpMessageConverter = new ResponseHttpMessageConverter(objectMapper);
    }

    protected CacheProvider verify(HttpInputMessage inputMessage) throws ParameterException {
        List<String> customer = inputMessage.getHeaders().get(XCacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER);
        List<String> store = inputMessage.getHeaders().get(XCacheConstants.HTTP_HEADER_FOR_CACHE_STORE);
        if (CollectionUtils.isEmpty(customer) || customer.size() > 1
                || CollectionUtils.isEmpty(store) || store.size() > 1) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }
        ConsumerIdentifier identifier = new ConsumerIdentifier(customer.getFirst(), store.getFirst());
        if (!cacheProviderSelector.getCacheProviderMap().containsKey(identifier)) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }

        return cacheProviderSelector.getCacheProviderMap().get(identifier);
    }

    protected ProviderCache encode(JsonNode jsonNode, CacheProvider cacheProvider) throws JsonProcessingException {
        StoreCache<Serializable> store = objectMapper.convertValue(jsonNode, new TypeReference<>() {
        });
        String data = objectMapper.writeValueAsString(store.getStore());
        store.setStore(objectMapper.writeValueAsBytes(data));
        Instant now = Instant.now();
        if(Boolean.TRUE.equals(cacheProvider.getStoreProps().getAddition().getStatical())){

        }
        if (jsonNode.hasNonNull("ttlSec")) {
            long ttlDiff = jsonNode.get("ttlSec").asLong() - cacheProvider.getStoreProps().getAddition().getTtl().getSeconds();
            store.setTtlEffectedDtm(now.plusSeconds(ttlDiff));
        } else {
            store.setTtlEffectedDtm(now);
        }

        return objectMapper.convertValue(store, new TypeReference<>() {
        });
    }

    protected StoreCache<Serializable> decode(ProviderCache cache) throws IOException {
        StoreCache<Serializable> result = objectMapper.convertValue(cache, new TypeReference<>() {
        });
        String data = objectMapper.readValue(cache.getStore(), String.class);
        result.setStore(objectMapper.readValue(data, new TypeReference<LinkedHashMap<String, Serializable>>() {
        }));

        return result;
    }

    @Nonnull
    @Override
    protected final T readInternal(@Nonnull Class<? extends T> clazz, @Nonnull HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
        try {
            JsonNode jsonNode = objectMapper.readTree(inputMessage.getBody());

            return this.readInternal(jsonNode.get("data"), this.verify(inputMessage));
        } catch (Exception e) {

            throw new HttpMessageNotReadableException(e.getMessage(), inputMessage);
        }
    }

    @Override
    protected final void writeInternal(@Nonnull T t, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Response<ResponseData> result;
        if (Objects.isNull(t.getData())) {
            result = ResponseUtils.success();
        } else {
            result = ResponseUtils.success(this.writeInternal(t.getData()));
        }
        responseHttpMessageConverter.write(result, null, this.getSupportedMediaTypes().getFirst(), outputMessage);
    }

    @Nonnull
    abstract T readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws HttpMessageNotReadableException, JsonProcessingException;

    @Nonnull
    abstract ResponseData writeInternal(@Nonnull Serializable data) throws IOException;

}
