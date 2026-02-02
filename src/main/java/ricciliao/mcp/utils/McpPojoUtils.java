package ricciliao.mcp.utils;

import ricciliao.mcp.pojo.po.McpProviderInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.x.component.persistence.LoggerAction;

public class McpPojoUtils {

    private McpPojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static LoggerAction.Dtm<McpProviderInfoLogPo> covert2Po(McpProviderInfoPo po,
                                                                   LoggerAction.Cd<McpProviderInfoLogPo> actionCd) {
        McpProviderInfoLogPo logPo = new McpProviderInfoLogPo();
        logPo.setId(po.getId());
        logPo.setConsumer(po.getConsumer());
        logPo.setStore(po.getStore());
        logPo.setProvider(po.getProvider());
        logPo.setTtlSeconds(po.getTtlSeconds());
        logPo.setIsActive(po.getIsActive());
        logPo.setIsStatic(po.getIsStatic());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());

        return actionCd.apply(logPo);
    }

    public static LoggerAction.Dtm<McpProviderPassInfoLogPo> convert2Po(McpProviderPassInfoPo po,
                                                                        LoggerAction.Cd<McpProviderPassInfoLogPo> actionCd) {
        McpProviderPassInfoLogPo logPo = new McpProviderPassInfoLogPo();
        logPo.setProviderInfoId(po.getProviderInfoId());
        logPo.setPassKey(po.getPassKey());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());

        return actionCd.apply(logPo);
    }

}
