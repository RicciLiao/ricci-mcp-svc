package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DuplicateException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.mcp.McpIdentifier;

public abstract class AbstractMcpProviderLifecycle {

    private final McpProviderRegistry registry;

    protected AbstractMcpProviderLifecycle(@Nonnull McpProviderRegistry registry) {
        this.registry = registry;
    }

    protected abstract void preCreation(@Nonnull McpProviderInfoPo po);

    protected abstract void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo po);

    protected abstract void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo po);

    protected abstract void postDestruction(@Nonnull McpProviderInfoPo po);

    protected final void delegatePreCreation(@Nonnull McpProviderInfoPo po) throws AbstractException {
        if (Boolean.TRUE.equals(this.registry.exists(new McpIdentifier(po.getConsumer(), po.getStore())))) {

            throw new DuplicateException(SecondaryCodeEnum.BLANK);
        }
        this.preCreation(po);
    }

    protected final void delegatePostCreation(@Nonnull AbstractMcpProvider provider,
                                              @Nonnull McpProviderInfoPo po) {
        this.postCreation(provider, po);
        this.registry.register(provider, po.getIsStatic());
    }

    protected final AbstractMcpProvider delegatePreDestruction(@Nonnull McpProviderInfoPo po) {
        AbstractMcpProvider provider = this.registry.get(new McpIdentifier(po.getConsumer(), po.getStore()));
        this.registry.unregister(new McpIdentifier(po.getConsumer(), po.getStore()));
        this.preDestruction(provider, po);

        return provider;
    }

    protected final void delegatePostDestruction(@Nonnull McpProviderInfoPo po) {
        this.postDestruction(po);
    }

}
