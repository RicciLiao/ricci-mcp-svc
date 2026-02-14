package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderTypeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.x.component.exception.AbstractException;

public interface McpProviderFactory {

    @Nonnull
    McpProviderTypeEnum whoAmI();

    @Nonnull
    AbstractMcpProvider create(@Nonnull McpProviderInfoBo bo) throws AbstractException;

    void destroy(@Nonnull McpProviderInfoBo provider);

}
