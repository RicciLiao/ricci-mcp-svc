package ricciliao.mcp.service;

import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.x.component.persistence.CrudService;

import java.util.List;

public interface McpProviderInfoService extends CrudService<McpProviderInfoDto> {

    McpProviderInfoBo fullyGet(Long id);

    List<McpProviderInfoBo> fullyList();

    String passkey(Long id);

}
