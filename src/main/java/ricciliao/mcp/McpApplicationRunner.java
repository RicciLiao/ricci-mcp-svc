package ricciliao.mcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.service.McpProviderInfoService;

import java.util.List;

@Component("mcpApplicationRunner")
public class McpApplicationRunner implements ApplicationRunner {

    private McpProviderInfoService providerInfoService;
    private McpProviderFactoryContext providerFactoryContext;

    @Autowired
    public void setProviderFactoryContext(McpProviderFactoryContext providerFactoryContext) {
        this.providerFactoryContext = providerFactoryContext;
    }

    @Autowired
    public void setProviderInfoService(McpProviderInfoService providerInfoService) {
        this.providerInfoService = providerInfoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<McpProviderInfoDto> dtoList = providerInfoService.list();
        for (McpProviderInfoDto dto : dtoList) {
            providerFactoryContext.create(providerInfoService.pipelinePreStartup(dto.getId()));
        }
    }

}
