package ricciliao.mcp.provider.impl.redis;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.lang.NonNull;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import ricciliao.mcp.properties.McpProviderProperties;
import ricciliao.mcp.properties.RedisProviderProperties;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class RedisClientFactory implements AbstractMcpProviderFactory.ClientFactory {

    @Override
    public JedisPooled create(@NonNull McpProviderProperties providerProperties) {

        return new JedisPooled(null, this.clientConfig((RedisProviderProperties) providerProperties, null), this.poolConfig(null));
    }

    public JedisClientConfig clientConfig(@Nonnull RedisProviderProperties providerProperties,
                                          @Nullable UnaryOperator<DefaultJedisClientConfig.Builder> apply) {

        DefaultJedisClientConfig.Builder builder =
                DefaultJedisClientConfig
                        .builder()
                        .hostAndPortMapper(mapper -> new HostAndPort(providerProperties.getHost(), providerProperties.getPort()))
                        .database(0)
                        .password(providerProperties.getPassword())
                        .user(providerProperties.getUsername());
        if (Objects.nonNull(apply)) {

            return apply.apply(builder).build();
        }

        return builder.build();

    }

    public <T> GenericObjectPoolConfig<T> poolConfig(@Nullable UnaryOperator<GenericObjectPoolConfig<T>> apply) {
        GenericObjectPoolConfig<T> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        if (Objects.nonNull(apply)) {

            return apply.apply(poolConfig);
        }

        return poolConfig;
    }

}
