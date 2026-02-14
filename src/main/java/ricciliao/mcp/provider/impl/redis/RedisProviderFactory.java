package ricciliao.mcp.provider.impl.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.util.ResourceUtils;
import redis.clients.jedis.JedisPooled;
import ricciliao.mcp.common.McpProviderTypeEnum;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
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
        this.upsertLua =
                SneakyThrowUtils.get(() -> Files.readString(Paths.get(ResourceUtils.getFile("classpath:redis_upsert.lua").getPath())));
    }

    @Nonnull
    @Override
    protected AbstractMcpProvider create(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) {
        RedisProviderProperties providerProperties = (RedisProviderProperties) this.providerProperties;
        providerProperties.setUsername(info.getConsumer());
        providerProperties.setPassword(passInfo.getPassKey());

        return
                new RedisProvider(
                        info,
                        (JedisPooled) this.clientFactory.create(providerProperties),
                        this.objectMapper,
                        this.upsertLua
                );
    }

    @Override
    protected void destroy(@Nonnull McpProviderInfoPo info, @Nonnull AbstractMcpProvider provider) {
        //do nothing
    }

    @Nonnull
    @Override
    public McpProviderTypeEnum whoAmI() {

        return McpProviderTypeEnum.REDIS;
    }

}
