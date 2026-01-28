package ricciliao.mcp.component;

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
import ricciliao.mcp.pojo.McpCacheMap;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.pojo.ProviderCacheMessage;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseHttpMessageConverter;
import ricciliao.x.component.payload.response.ResponseUtils;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.mcp.McpCache;
import ricciliao.x.mcp.McpConstants;
import ricciliao.x.mcp.McpIdentifier;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public abstract class McpCacheMessageConverter<T extends ProviderCacheMessage<? extends Serializable>> extends AbstractHttpMessageConverter<T> {

    protected final ObjectMapper objectMapper;
    private final McpProviderRegistry mcpProviderRegistry;
    private final ResponseHttpMessageConverter responseHttpMessageConverter;

    protected McpCacheMessageConverter(ObjectMapper objectMapper, McpProviderRegistry mcpProviderRegistry) {
        super(MediaType.APPLICATION_JSON);
        this.objectMapper = objectMapper;
        this.mcpProviderRegistry = mcpProviderRegistry;
        this.responseHttpMessageConverter = new ResponseHttpMessageConverter(objectMapper);
    }

    protected AbstractMcpProvider verify(HttpInputMessage inputMessage) throws ParameterException {
        List<String> customer = inputMessage.getHeaders().get(McpConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER);
        List<String> store = inputMessage.getHeaders().get(McpConstants.HTTP_HEADER_FOR_CACHE_STORE);
        if (CollectionUtils.isEmpty(customer) || customer.size() > 1
                || CollectionUtils.isEmpty(store) || store.size() > 1) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }
        McpIdentifier identifier = new McpIdentifier(customer.getFirst(), store.getFirst());
        if (!Boolean.TRUE.equals(mcpProviderRegistry.exists(identifier))) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }

        return mcpProviderRegistry.get(identifier);
    }

    protected ProviderCache encode(JsonNode jsonNode, AbstractMcpProvider cacheProvider) throws JsonProcessingException {
        McpCache<Serializable> cache = objectMapper.convertValue(jsonNode, new TypeReference<>() {
        });
        cache.setData(objectMapper.writeValueAsBytes(cache.getData()));
        Instant now = Instant.now();
        if (jsonNode.hasNonNull("ttlSec")) {
            long ttlDiff = jsonNode.get("ttlSec").asLong() - cacheProvider.getTtlSeconds().getSeconds();
            cache.setTtlEffectedDtm(now.plusSeconds(ttlDiff));
        } else {
            cache.setTtlEffectedDtm(now);
        }

        return objectMapper.convertValue(cache, new TypeReference<>() {
        });
    }

    protected McpCacheMap decode(ProviderCache cache) throws IOException {
        byte[] data = cache.getData();
        cache.setData(null);
        McpCacheMap result = objectMapper.convertValue(cache, new TypeReference<>() {
        });
        result.setData(objectMapper.readValue(data, new TypeReference<>() {
        }));

        return result;
    }

    @Nonnull
    @Override
    protected final T readInternal(@Nonnull Class<? extends T> clazz, @Nonnull HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
        try {

            return this.readInternal(objectMapper.readTree(inputMessage.getBody()), this.verify(inputMessage));
        } catch (Exception e) {

            throw new HttpMessageNotReadableException(e.getMessage(), inputMessage);
        }
    }

    @Override
    protected final void writeInternal(@Nonnull T t, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Response<PayloadData> result;
        if (Objects.isNull(t.getData())) {
            result = ResponseUtils.success();
        } else {
            result = ResponseUtils.success(this.writeInternal(t.getData()));
        }
        responseHttpMessageConverter.write(result, null, this.getSupportedMediaTypes().getFirst(), outputMessage);
    }

    @Nonnull
    abstract T readInternal(@Nonnull JsonNode node, AbstractMcpProvider cacheProvider) throws HttpMessageNotReadableException, JsonProcessingException;

    @Nonnull
    abstract PayloadData writeInternal(@Nonnull Serializable data) throws IOException;
}
