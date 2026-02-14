package ricciliao.mcp.common;

public enum McpProviderTypeEnum {
    REDIS("redis"),
    MONGO("mongo"),
    ;

    private final String provider;


    McpProviderTypeEnum(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

}
