package ricciliao.mcp.utils;

import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.pojo.dto.McpProviderPassInfoDto;
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
        logPo.setActive(po.getActive());
        logPo.setStatical(po.getStatical());
        logPo.setCreatedBy(po.getCreatedBy());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedBy(po.getUpdatedBy());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());
        logPo.setActionBy(po.getUpdatedBy());

        return actionCd.apply(logPo);
    }

    public static LoggerAction.Dtm<McpProviderPassInfoLogPo> convert2Po(McpProviderPassInfoPo po,
                                                                        LoggerAction.Cd<McpProviderPassInfoLogPo> actionCd) {
        McpProviderPassInfoLogPo logPo = new McpProviderPassInfoLogPo();
        logPo.setProviderInfoId(po.getProviderInfoId());
        logPo.setPassKey(po.getPassKey());
        logPo.setCreatedBy(po.getCreatedBy());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedBy(po.getUpdatedBy());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());
        logPo.setActionBy(po.getUpdatedBy());

        return actionCd.apply(logPo);
    }

    public static McpProviderInfoDto convert2Dto(McpProviderInfoPo po) {
        McpProviderInfoDto dto = new McpProviderInfoDto();
        dto.setId(po.getId());
        dto.setConsumer(po.getConsumer());
        dto.setStore(po.getStore());
        dto.setProvider(po.getProvider());
        dto.setTtlSeconds(po.getTtlSeconds());
        dto.setActive(po.getActive());
        dto.setStatical(po.getStatical());
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(po.getCreatedDtm());
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(po.getUpdatedDtm());
        dto.setVersion(po.getVersion());

        return dto;
    }

    public static McpProviderPassInfoDto convert2Dto(McpProviderPassInfoPo po) {
        McpProviderPassInfoDto dto = new McpProviderPassInfoDto();
        dto.setProviderInfoId(po.getProviderInfoId());
        dto.setPassKey(po.getPassKey());
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(po.getCreatedDtm());
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(po.getUpdatedDtm());
        dto.setVersion(po.getVersion());

        return dto;
    }

    public static McpProviderInfoPo convert2Po(McpProviderInfoDto dto) {
        McpProviderInfoPo po = new McpProviderInfoPo();
        po.setId(dto.getId());
        po.setConsumer(dto.getConsumer());
        po.setStore(dto.getStore());
        po.setProvider(dto.getProvider());
        po.setTtlSeconds(dto.getTtlSeconds());
        po.setActive(dto.getActive());
        po.setStatical(dto.getStatical());
        po.setCreatedBy(dto.getCreatedBy());
        po.setCreatedDtm(dto.getCreatedDtm());
        po.setUpdatedBy(dto.getUpdatedBy());
        po.setUpdatedDtm(dto.getUpdatedDtm());
        po.setVersion(dto.getVersion());

        return po;
    }

    public static McpProviderPassInfoPo convert2Po(McpProviderPassInfoDto dto) {
        McpProviderPassInfoPo po = new McpProviderPassInfoPo();
        po.setProviderInfoId(dto.getProviderInfoId());
        po.setPassKey(dto.getPassKey());
        po.setCreatedBy(dto.getCreatedBy());
        po.setCreatedDtm(dto.getCreatedDtm());
        po.setUpdatedBy(dto.getUpdatedBy());
        po.setUpdatedDtm(dto.getUpdatedDtm());
        po.setVersion(dto.getVersion());

        return po;
    }

}
