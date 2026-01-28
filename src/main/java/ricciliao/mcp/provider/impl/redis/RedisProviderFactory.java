package ricciliao.mcp.provider.impl.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.JedisPooled;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.properties.RedisProviderProperties;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class RedisProviderFactory extends AbstractMcpProviderFactory {

    private final ObjectMapper objectMapper;
    private final String upsertLua;

    public RedisProviderFactory(@Nonnull RedisProviderProperties providerProperties,
                                @Nonnull RedisClientFactory jedisPooledFactory,
                                @Nonnull RedisProviderLifecycle redisProviderLifecycle,
                                @Nonnull ObjectMapper objectMapper) {
        super(providerProperties, jedisPooledFactory, redisProviderLifecycle);
        this.objectMapper = objectMapper;
        this.upsertLua = SneakyThrowUtils.get(() -> Files.readString(Paths.get(ResourceUtils.getFile("classpath:redis_upsert.lua").getPath())));
    }

    @Override
    protected AbstractMcpProvider create(@Nonnull McpProviderInfoPo po) {
        RedisProviderProperties providerProperties = this.getProviderProperties();

        return
                new RedisProvider(
                        po,
                        (JedisPooled) this.getClientFactory().create(providerProperties),
                        this.objectMapper,
                        this.upsertLua
                );
    }

    @Override
    public McpProviderEnum whoAmI() {

        return McpProviderEnum.REDIS;
    }

    @Override
    public RedisProviderProperties getProviderProperties() {

        return (RedisProviderProperties) super.getProviderProperties();
    }
}
