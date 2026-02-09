package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.properties.McpProviderProperties;
import ricciliao.x.component.exception.AbstractException;

public abstract class AbstractMcpProviderFactory implements McpProviderFactory {

    protected final McpProviderProperties providerProperties;
    protected final AbstractMcpProviderFactory.ClientFactory clientFactory;
    protected final AbstractMcpProviderLifecycle lifecycle;

    protected AbstractMcpProviderFactory(McpProviderProperties providerProperties,
                                         AbstractMcpProviderFactory.ClientFactory clientFactory,
                                         AbstractMcpProviderLifecycle lifecycle) {
        this.providerProperties = providerProperties;
        this.clientFactory = clientFactory;
        this.lifecycle = lifecycle;
    }

    @Nonnull
    @Override
    public AbstractMcpProvider create(@Nonnull McpProviderInfoBo bo) throws AbstractException {
        this.lifecycle.preCreation(bo);
        AbstractMcpProvider provider = this.create(bo.getInfo(), bo.getPassInfo());
        this.lifecycle.postCreation(provider, bo);

        return provider;
    }

    @Override
    public void destroy(@Nonnull McpProviderInfoBo bo) {
        AbstractMcpProvider provider = this.lifecycle.preDestruction(bo);
        this.destroy(bo.getInfo(), provider);
        provider.destroy();
        this.lifecycle.postDestruction(bo);
    }

    @Nonnull
    protected abstract AbstractMcpProvider create(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) throws AbstractException;

    protected abstract void destroy(@Nonnull McpProviderInfoPo info, @Nonnull AbstractMcpProvider provider);

    public interface ClientFactory {

        @Nonnull
        Object create(@Nonnull McpProviderProperties providerProperties);

    }

}
