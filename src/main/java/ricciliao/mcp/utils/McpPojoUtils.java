package ricciliao.mcp.utils;

import ricciliao.mcp.pojo.po.McpProviderInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPasskeyLogPo;
import ricciliao.mcp.pojo.po.McpProviderPasskeyPo;
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

        return actionCd.apply(logPo);
    }

    public static LoggerAction.Dtm<McpProviderPasskeyLogPo> convert2Po(McpProviderPasskeyPo po,
                                                                       LoggerAction.Cd<McpProviderPasskeyLogPo> actionCd) {
        McpProviderPasskeyLogPo logPo = new McpProviderPasskeyLogPo();
        logPo.setProviderInfoId(po.getProviderInfoId());
        logPo.setPassKey(po.getPassKey());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());

        return actionCd.apply(logPo);
    }

}
