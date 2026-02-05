package ricciliao.mcp.service;

import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.x.component.exception.AbstractException;

public interface ProviderService {

    boolean initialize() throws AbstractException;

    Long create(McpProviderInfoDto dto) throws AbstractException;

}
