package ricciliao.mcp.common;


import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum McpSecondaryCodeEnum implements SecondaryCode {

    CONSUMER_STORE_EXISTED(1, "Provider {}-{} already exists."),
    PROVIDER_FACTORY_NOT_EXISTED(1, "ProviderFactory [{}_{}] does not exist."),
    PROVIDER_NOT_FIND(2, "Cannot find the Provider [{}_{}]."),
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
