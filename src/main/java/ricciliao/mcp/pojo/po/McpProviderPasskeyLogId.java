package ricciliao.mcp.pojo.po;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class McpProviderPasskeyLogId implements Serializable {
    @Serial
    private static final long serialVersionUID = 9090437831320656556L;

    private Long providerInfoId;
    private Instant actionDtm;

    public McpProviderPasskeyLogId() {
    }

    public McpProviderPasskeyLogId(Long providerInfoId, Instant actionDtm) {
        this.providerInfoId = providerInfoId;
        this.actionDtm = actionDtm;
    }

    public Long getProviderInfoId() {
        return providerInfoId;
    }

    public void setProviderInfoId(Long providerInfoId) {
        this.providerInfoId = providerInfoId;
    }

    public Instant getActionDtm() {
        return actionDtm;
    }

    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        McpProviderPasskeyLogId entity = (McpProviderPasskeyLogId) o;
        return Objects.equals(this.providerInfoId, entity.providerInfoId) &&
                Objects.equals(this.actionDtm, entity.actionDtm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerInfoId, actionDtm);
    }
}