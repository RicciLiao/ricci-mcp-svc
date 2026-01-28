package ricciliao.mcp.provider;


import jakarta.annotation.Nullable;
import ricciliao.x.mcp.McpIdentifier;

import java.util.HashMap;
import java.util.Map;

public class McpProviderRegistry {

    private final Map<McpIdentifier, Bo> mcpProviderMap;

    public McpProviderRegistry() {
        this.mcpProviderMap = new HashMap<>();
    }

    protected void register(AbstractMcpProvider mcpProvider, boolean isStatic) {
        this.mcpProviderMap.put(mcpProvider.getIdentifier(), new Bo(mcpProvider, isStatic));
    }

    protected void unregister(@Nullable McpIdentifier identifier) {
        this.mcpProviderMap.remove(identifier);
    }

    public AbstractMcpProvider get(@Nullable McpIdentifier identifier) {

        return this.mcpProviderMap.get(identifier).mcpProvider();
    }

    public Boolean isStatic(@Nullable McpIdentifier identifier) {

        return this.mcpProviderMap.get(identifier).statical();
    }

    public Boolean exists(@Nullable McpIdentifier identifier) {

        return this.mcpProviderMap.containsKey(identifier);
    }

    record Bo(AbstractMcpProvider mcpProvider, Boolean statical) {
    }

}
