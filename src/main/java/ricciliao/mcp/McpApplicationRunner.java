package ricciliao.mcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.mcp.service.McpProviderPipeline;

@Component("mcpApplicationRunner")
public class McpApplicationRunner implements ApplicationRunner {

    private McpProviderPipeline providerPipeline;

    @Autowired
    public void setProviderPipeline(McpProviderPipeline providerPipeline) {
        this.providerPipeline = providerPipeline;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        providerPipeline.startup();
    }

}
