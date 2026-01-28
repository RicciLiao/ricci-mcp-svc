package ricciliao.mcp.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ricciliao.x.starter.XProperties;

@EnableConfigurationProperties(value = {
        MongoProviderProperties.class,
        RedisProviderProperties.class,
})
@Configuration("cacheProviderProperties")
public class McpProperties {

    private final RedisProviderProperties redisProviderProps;
    private final MongoProviderProperties mongoProviderProps;
    private final XProperties xProps;

    public McpProperties(@Autowired RedisProviderProperties redisCacheProperties,
                         @Autowired MongoProviderProperties mongoCacheProperties,
                         @Autowired XProperties xProperties) {
        super();
        this.redisProviderProps = redisCacheProperties;
        this.mongoProviderProps = mongoCacheProperties;
        this.xProps = xProperties;
    }

    public RedisProviderProperties getRedisProviderProps() {
        return redisProviderProps;
    }

    public MongoProviderProperties getMongoProviderProps() {
        return mongoProviderProps;
    }

    public XProperties getxProps() {
        return xProps;
    }
}
