package ricciliao.mcp.common;


import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum McpSecondaryCodeEnum implements SecondaryCode {

    CONSUMER_STORE_EXISTED(1, "Provider {}-{} already exists."),
    PROVIDER_FACTORY_NOT_EXISTED(2, "ProviderFactory [{}_{}] does not exist."),
    PROVIDER_NOT_FIND(3, "Cannot find the Provider [{}_{}]."),
    PROVIDER_FACTORY_EXISTED(4, "ProviderFactory [{}_{}] already exist."),
    ;

    private final int id;
    private final String message;

    McpSecondaryCodeEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
