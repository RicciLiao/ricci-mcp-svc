package ricciliao.mcp.common;


import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum McpSecondaryCodeEnum implements SecondaryCode {

    PROVIDER_NOT_EXISTED(1, "Can not find the provider factory with {}."),
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
