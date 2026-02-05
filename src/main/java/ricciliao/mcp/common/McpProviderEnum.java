package ricciliao.mcp.common;

public enum McpProviderEnum {
    REDIS("redis"),
    MONGO("mongo"),
    ;

    private final String provider;


    McpProviderEnum(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

}
