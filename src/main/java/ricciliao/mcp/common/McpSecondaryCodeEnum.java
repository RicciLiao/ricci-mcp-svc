package ricciliao.mcp.common;


import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum McpSecondaryCodeEnum implements SecondaryCode {

    CONSUMER_STORE_EXISTED(1, "Provider [{}_{}] already exists."),
    PROVIDER_FACTORY_NOT_EXISTED(2, "ProviderFactory [{}] does not exist."),
    PROVIDER_NOT_FIND(3, "Cannot find the Provider [{}_{}]."),
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
