package ricciliao.mcp.service;

import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.x.component.exception.AbstractException;

import java.util.List;

public interface McpProviderPipeline {

    boolean startup() throws AbstractException;

    Long create(McpProviderInfoDto dto) throws AbstractException;

    Long update(McpProviderInfoDto dto) throws AbstractException;

    Long delete(McpProviderInfoDto dto) throws AbstractException;

    boolean batch(List<McpProviderInfoDto> dtoList) throws AbstractException;

}
