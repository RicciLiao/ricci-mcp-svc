package ricciliao.mcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.mcp.service.ProviderService;

@Component("mcpApplicationRunner")
public class McpApplicationRunner implements ApplicationRunner  {

    private ProviderService providerService;

    @Autowired
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        providerService.initialize();
    }

}
