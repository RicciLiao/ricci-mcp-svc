package ricciliao.mcp.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;
import ricciliao.mcp.properties.RedisProviderProperties;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.mcp.provider.impl.redis.RedisClientFactory;
import ricciliao.mcp.provider.impl.redis.RedisProviderFactory;
import ricciliao.mcp.provider.impl.redis.RedisProviderLifecycle;

@AutoConfiguration
@EnableConfigurationProperties(RedisProviderProperties.class)
public class RedisProviderAutoConfiguration {

    @Bean
    public RedisClientFactory jedisPooledFactory() {

        return new RedisClientFactory();
    }

    @Bean("authJedisPool")
    public JedisPool authJedisPool(@Autowired RedisProviderProperties providerProperties,
                                   @Autowired RedisClientFactory redisClientFactory) {

        return
                new JedisPool(
                        redisClientFactory.poolConfig(null),
                        (HostAndPort) null,
                        redisClientFactory.clientConfig(providerProperties, null)
                );
    }

    @Bean("authJedisPooled")
    public JedisPooled authJedisPooled(@Autowired RedisProviderProperties providerProperties,
                                       @Autowired RedisClientFactory redisClientFactory) {

        return redisClientFactory.create(providerProperties);
    }

    @Bean
    public RedisProviderLifecycle redisProviderLifecycle(@Autowired McpProviderRegistry mcpProviderRegistry,
                                                         @Autowired JedisPooled authJedisPooled) {

        return new RedisProviderLifecycle(mcpProviderRegistry, authJedisPooled);
    }

    @Bean
    public RedisProviderFactory jedisProviderFactory(@Autowired RedisProviderProperties properties,
                                                     @Autowired RedisClientFactory jedisPooledFactory,
                                                     @Autowired RedisProviderLifecycle redisProviderLifecycle,
                                                     @Autowired ObjectMapper objectMapper) {

        return new RedisProviderFactory(properties, jedisPooledFactory, redisProviderLifecycle, objectMapper);
    }

}
