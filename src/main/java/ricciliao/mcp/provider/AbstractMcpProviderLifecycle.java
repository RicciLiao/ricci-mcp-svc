package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import ricciliao.mcp.common.McpSecondaryCodeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.mcp.McpIdentifier;

public abstract class AbstractMcpProviderLifecycle implements McpProviderLifecycle {

    final McpProviderRegistry registry;

    protected AbstractMcpProviderLifecycle(@Nonnull McpProviderRegistry registry) {
        this.registry = registry;
    }

    protected abstract void preCreation(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) throws AbstractException;

    protected abstract void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info);

    protected abstract void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info);

    protected abstract void postDestruction(@Nonnull McpProviderInfoPo info);

    @Override
    public void preCreation(@Nonnull McpProviderInfoBo bo) throws AbstractException {
        if (Boolean.TRUE.equals(this.registry.exists(new McpIdentifier(bo.getInfo().getConsumer(), bo.getInfo().getStore())))) {

            throw new DataException(McpSecondaryCodeEnum.PROVIDER_NOT_FIND);
        }
        this.preCreation(bo.getInfo(), bo.getPassInfo());
    }

    @Override
    public void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoBo bo) {
        this.postCreation(provider, bo.getInfo());
        this.registry.register(provider, bo.getInfo().getStatical());
    }

    @Nullable
    @Override
    public AbstractMcpProvider preDestruction(@Nonnull McpProviderInfoBo bo) {
        McpIdentifier identifier = new McpIdentifier(bo.getInfo().getConsumer(), bo.getInfo().getStore());
        if (!Boolean.TRUE.equals(this.registry.exists(identifier))) {

            return null;
        }
        AbstractMcpProvider provider = this.registry.get(identifier);
        this.registry.unregister(identifier);
        this.preDestruction(provider, bo.getInfo());

        return provider;
    }

    @Override
    public void postDestruction(@Nonnull McpProviderInfoBo bo) {
        this.postDestruction(bo.getInfo());
    }
}
