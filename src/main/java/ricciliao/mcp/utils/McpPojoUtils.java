package ricciliao.mcp.utils;

import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.pojo.dto.McpProviderPassInfoDto;
import ricciliao.mcp.pojo.dto.McpProviderStatusDto;
import ricciliao.mcp.pojo.po.McpProviderInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoLogPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.pojo.po.McpProviderStatusLogPo;
import ricciliao.mcp.pojo.po.McpProviderStatusPo;
import ricciliao.x.component.persistence.LogAction;
import ricciliao.x.component.persistence.ModifiableAction;

public class McpPojoUtils {

    private McpPojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static McpProviderInfoLogPo convert2Po(McpProviderInfoPo po,
                                                  LogAction.Op<McpProviderInfoLogPo> op) {
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

        return op.apply(logPo);
    }

    public static McpProviderPassInfoLogPo convert2Po(McpProviderPassInfoPo po,
                                                      LogAction.Op<McpProviderPassInfoLogPo> op) {
        McpProviderPassInfoLogPo logPo = new McpProviderPassInfoLogPo();
        logPo.setProviderInfoId(po.getProviderInfoId());
        logPo.setPassKey(po.getPassKey());
        logPo.setCreatedBy(po.getCreatedBy());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedBy(po.getUpdatedBy());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());
        logPo.setActionBy(po.getUpdatedBy());

        return op.apply(logPo);
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

    public static McpProviderInfoPo convert2Po(McpProviderInfoDto dto,
                                               ModifiableAction.Op<McpProviderInfoPo> op) {
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

        return op.apply(po);
    }

    public static McpProviderStatusLogPo convert2Po(McpProviderStatusPo po,
                                                    LogAction.Op<McpProviderStatusLogPo> op) {
        McpProviderStatusLogPo logPo = new McpProviderStatusLogPo();
        logPo.setProviderInfoId(po.getProviderInfoId());
        logPo.setStatus(po.getStatus());
        logPo.setCreatedBy(po.getCreatedBy());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedBy(po.getUpdatedBy());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());

        return op.apply(logPo);
    }

    public static McpProviderStatusDto convert2Dto(McpProviderStatusPo po) {
        McpProviderStatusDto dto = new McpProviderStatusDto();
        dto.setProviderInfoId(po.getProviderInfoId());
        dto.setStatus(po.getStatus());
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(po.getCreatedDtm());
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(po.getUpdatedDtm());
        dto.setVersion(po.getVersion());

        return dto;
    }
}
