package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.properties.McpProviderProperties;
import ricciliao.x.component.exception.AbstractException;

public abstract class AbstractMcpProviderFactory {

    private final McpProviderProperties providerProperties;
    private final AbstractMcpProviderFactory.ClientFactory clientFactory;
    private final AbstractMcpProviderLifecycle lifecycle;

    protected AbstractMcpProviderFactory(McpProviderProperties providerProperties,
                                         AbstractMcpProviderFactory.ClientFactory clientFactory,
                                         AbstractMcpProviderLifecycle lifecycle) {
        this.providerProperties = providerProperties;
        this.clientFactory = clientFactory;
        this.lifecycle = lifecycle;
    }

    public abstract McpProviderEnum whoAmI();

    protected abstract AbstractMcpProvider create(@Nonnull McpProviderInfoPo po);

    protected void destroy(@Nonnull AbstractMcpProvider provider) {
        provider.destroy();
    }

    public McpProviderProperties getProviderProperties() {

        return this.providerProperties.copy();
    }

    public final AbstractMcpProviderFactory.ClientFactory getClientFactory() {

        return clientFactory;
    }

    protected final AbstractMcpProvider delegateCreate(@Nonnull McpProviderInfoPo po) throws AbstractException {
        this.lifecycle.delegatePreCreation(po);
        AbstractMcpProvider provider = this.create(po);
        this.lifecycle.delegatePostCreation(provider, po);

        return provider;
    }

    protected final void delegateDestroy(@Nonnull McpProviderInfoPo po) {
        AbstractMcpProvider provider = this.lifecycle.delegatePreDestruction(po);
        this.destroy(provider);
        this.lifecycle.delegatePostDestruction(po);
    }

    public interface ClientFactory {
        Object create(@Nonnull McpProviderProperties providerProperties);
    }

}
