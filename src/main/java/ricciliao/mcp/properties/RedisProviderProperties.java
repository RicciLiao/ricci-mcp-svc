package ricciliao.mcp.properties;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;

@ConfigurationProperties("mcp.redis")
public class RedisProviderProperties implements McpProviderProperties {
    @Serial
    private static final long serialVersionUID = 5664178907058712275L;

    private String host;
    private Integer port;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public RedisProperties createSpringProperties() {
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost(this.getHost());
        redisProperties.setPort(this.getPort());
        redisProperties.setUsername(this.getUsername());
        redisProperties.setPassword(this.getPassword());

        return redisProperties;
    }

    @Override
    public RedisProviderProperties copy() {

        return (RedisProviderProperties) McpProviderProperties.super.copy();
    }
}
