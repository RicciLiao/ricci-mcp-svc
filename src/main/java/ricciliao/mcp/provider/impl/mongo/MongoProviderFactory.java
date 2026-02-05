package ricciliao.mcp.provider.impl.mongo;

import com.mongodb.client.MongoClient;
import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.properties.MongoProviderProperties;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;

public class MongoProviderFactory extends AbstractMcpProviderFactory {

    public MongoProviderFactory(@Nonnull MongoProviderProperties properties,
                                @Nonnull MongoClientFactory mongoClientFactory,
                                @Nonnull MongoProviderLifecycle mongoProviderLifecycle) {
        super(properties, mongoClientFactory, mongoProviderLifecycle);
    }

    @Override
    protected MongoProvider create(@Nonnull McpProviderInfoBo bo) {
        MongoProviderProperties providerProperties = this.getProviderProperties();
        providerProperties.setUsername(bo.getInfo().getConsumer());
        providerProperties.setDatabase(bo.getInfo().getConsumer());
        providerProperties.setPassword(bo.getPassInfo().getPassKey().toCharArray());

        return new MongoProvider(bo.getInfo(), (MongoClient) super.getClientFactory().create(providerProperties));
    }

    @Override
    public McpProviderEnum whoAmI() {

        return McpProviderEnum.MONGO;
    }

    @Override
    public MongoProviderProperties getProviderProperties() {

        return (MongoProviderProperties) super.getProviderProperties();
    }
}
