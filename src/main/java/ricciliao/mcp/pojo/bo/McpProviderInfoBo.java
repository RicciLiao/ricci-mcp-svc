package ricciliao.mcp.pojo.bo;

import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.pojo.po.McpProviderTypePo;

import java.io.Serial;
import java.io.Serializable;

public class McpProviderInfoBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 5391559321370743634L;

    public McpProviderInfoBo() {
    }

    public McpProviderInfoBo(McpProviderInfoPo info,
                             McpProviderPassInfoPo passInfo,
                             McpProviderTypePo type) {
        this.info = info;
        this.passInfo = passInfo;
        this.type = type;
    }

    private McpProviderInfoPo info;
    private McpProviderPassInfoPo passInfo;
    private McpProviderTypePo type;

    public McpProviderInfoPo getInfo() {
        return info;
    }

    public void setInfo(McpProviderInfoPo info) {
        this.info = info;
    }

    public McpProviderPassInfoPo getPassInfo() {
        return passInfo;
    }

    public void setPassInfo(McpProviderPassInfoPo passInfo) {
        this.passInfo = passInfo;
    }

    public McpProviderTypePo getType() {
        return type;
    }

    public void setType(McpProviderTypePo type) {
        this.type = type;
    }
}
