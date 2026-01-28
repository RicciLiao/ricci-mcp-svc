package ricciliao.mcp.configuration;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ricciliao.mcp.properties.MongoProviderProperties;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.mcp.provider.impl.mongo.MongoClientFactory;
import ricciliao.mcp.provider.impl.mongo.MongoProviderFactory;
import ricciliao.mcp.provider.impl.mongo.MongoProviderLifecycle;

@AutoConfiguration
@EnableConfigurationProperties(MongoProviderProperties.class)
public class MongoProviderAutoConfiguration {

    @Bean
    public MongoClientFactory mongoClientFactory(@Autowired MongoProviderProperties providerProperties) {

        return new MongoClientFactory(providerProperties.getJks());
    }

    @Bean("authMongoClient")
    public MongoClient authMongoClient(@Autowired MongoClientFactory mongoClientFactory,
                                       @Autowired MongoProviderProperties providerProperties) {

        return mongoClientFactory.create(providerProperties);
    }

    @Bean
    public MongoProviderLifecycle mongoProviderLifecycle(@Autowired McpProviderRegistry mcpProviderRegistry,
                                                         @Autowired MongoClient authMongoClient,
                                                         @Autowired MongoProviderProperties providerProperties) {

        return new MongoProviderLifecycle(mcpProviderRegistry, authMongoClient, providerProperties.getAuthenticationDatabase());
    }

    @Bean
    public MongoProviderFactory mongoProviderFactory(@Autowired MongoProviderProperties properties,
                                                     @Autowired MongoClientFactory mongoClientFactory,
                                                     @Autowired MongoProviderLifecycle mongoProviderLifecycle) {

        return new MongoProviderFactory(properties, mongoClientFactory, mongoProviderLifecycle);
    }

}
