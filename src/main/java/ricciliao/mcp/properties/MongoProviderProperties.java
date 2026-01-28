package ricciliao.mcp.properties;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;


@ConfigurationProperties("mcp.mongo")
public class MongoProviderProperties implements McpProviderProperties {
    @Serial
    private static final long serialVersionUID = -2267205616485385275L;

    private String host;
    private Integer port;
    private String username;
    private char[] password;
    private String database;
    private String authenticationDatabase;
    private Jks jks;

    public Jks getJks() {
        return jks;
    }

    public void setJks(Jks jks) {
        this.jks = jks;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public MongoProperties createSpringProperties() {
        MongoProperties mongoProperties = new MongoProperties();
        mongoProperties.setHost(this.getHost());
        mongoProperties.setAuthenticationDatabase(this.getAuthenticationDatabase());
        mongoProperties.setUsername(this.getUsername());
        mongoProperties.setPassword(this.getPassword());
        mongoProperties.setPort(this.getPort());
        mongoProperties.setDatabase(this.getDatabase());

        return mongoProperties;
    }

    @Override
    public MongoProviderProperties copy() {

        return (MongoProviderProperties) McpProviderProperties.super.copy();
    }

    public static class Jks implements Serializable {
        @Serial
        private static final long serialVersionUID = 5949436091169607012L;

        private String path;
        private char[] password;

        public char[] getPassword() {
            return password;
        }

        public void setPassword(char[] password) {
            this.password = password;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

}
