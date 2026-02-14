package ricciliao.mcp.provider.impl.mongo;

import com.mongodb.client.MongoClient;
import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderTypeEnum;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.properties.MongoProviderProperties;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;
import ricciliao.x.component.exception.AbstractException;

public class MongoProviderFactory extends AbstractMcpProviderFactory {

    public MongoProviderFactory(@Nonnull MongoProviderProperties properties,
                                @Nonnull MongoClientFactory mongoClientFactory,
                                @Nonnull MongoProviderLifecycle mongoProviderLifecycle) {
        super(properties, mongoClientFactory, mongoProviderLifecycle);
    }

    @Nonnull
    @Override
    protected AbstractMcpProvider create(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) throws AbstractException {
        MongoProviderProperties providerProperties = (MongoProviderProperties) this.providerProperties;
        providerProperties.setUsername(info.getConsumer());
        providerProperties.setDatabase(info.getConsumer());
        providerProperties.setPassword(passInfo.getPassKey().toCharArray());

        return new MongoProvider(info, (MongoClient) this.clientFactory.create(providerProperties));
    }

    @Override
    protected void destroy(@Nonnull McpProviderInfoPo info, @Nonnull AbstractMcpProvider provider) {
        //do nothing
    }

    @Nonnull
    @Override
    public McpProviderTypeEnum whoAmI() {

        return McpProviderTypeEnum.MONGO;
    }

}
