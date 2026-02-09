package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.x.component.exception.AbstractException;

public interface McpProviderLifecycle {

    void preCreation(@Nonnull McpProviderInfoBo bo) throws AbstractException;

    void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoBo bo);

    @Nonnull
    AbstractMcpProvider preDestruction(@Nonnull McpProviderInfoBo bo);

    void postDestruction(@Nonnull McpProviderInfoBo bo);

}
