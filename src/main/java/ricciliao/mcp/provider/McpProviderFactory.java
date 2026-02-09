package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.x.component.exception.AbstractException;

public interface McpProviderFactory {

    @Nonnull
    McpProviderEnum whoAmI();

    @Nonnull
    AbstractMcpProvider create(@Nonnull McpProviderInfoBo bo) throws AbstractException;

    void destroy(@Nonnull McpProviderInfoBo provider);

}
