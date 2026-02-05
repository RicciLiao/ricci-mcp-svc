package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DuplicateException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.mcp.McpIdentifier;

public abstract class AbstractMcpProviderLifecycle {

    private final McpProviderRegistry registry;

    protected AbstractMcpProviderLifecycle(@Nonnull McpProviderRegistry registry) {
        this.registry = registry;
    }

    protected abstract void preCreation(@Nonnull McpProviderInfoBo bo);

    protected abstract void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoBo bo);

    protected abstract void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoBo bo);

    protected abstract void postDestruction(@Nonnull McpProviderInfoBo bo);

    protected final void delegatePreCreation(@Nonnull McpProviderInfoBo bo) throws AbstractException {
        if (Boolean.TRUE.equals(this.registry.exists(new McpIdentifier(bo.getInfo().getConsumer(), bo.getInfo().getStore())))) {

            throw new DuplicateException(SecondaryCodeEnum.BLANK);
        }
        this.preCreation(bo);
    }

    protected final void delegatePostCreation(@Nonnull AbstractMcpProvider provider,
                                              @Nonnull McpProviderInfoBo po) {
        this.postCreation(provider, po);
        this.registry.register(provider, po.getInfo().getStatical());
    }

    protected final AbstractMcpProvider delegatePreDestruction(@Nonnull McpProviderInfoBo bo) {
        McpIdentifier identifier = new McpIdentifier(bo.getInfo().getConsumer(), bo.getInfo().getStore());
        AbstractMcpProvider provider = this.registry.get(identifier);
        this.registry.unregister(identifier);
        this.preDestruction(provider, bo);

        return provider;
    }

    protected final void delegatePostDestruction(@Nonnull McpProviderInfoBo bo) {
        this.postDestruction(bo);
    }

}
