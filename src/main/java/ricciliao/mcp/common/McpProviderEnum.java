package ricciliao.mcp.common;

public enum McpProviderEnum {
    REDIS("redis", 0L),
    MONGO("mongo", 1L),
    ;

    private final String provider;
    private final Long identity;


    McpProviderEnum(String provider, Long identity) {
        this.provider = provider;
        this.identity = identity;
    }

    public String getProvider() {
        return provider;
    }

    public Long getIdentity() {
        return identity;
    }
}
