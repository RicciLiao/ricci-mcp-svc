package ricciliao.mcp.pojo.po;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class McpProviderStatusLogId implements Serializable {
    private Long providerInfoId;

    private Instant actionDtm;

    public McpProviderStatusLogId() {
    }

    public McpProviderStatusLogId(Long providerInfoId, Instant actionDtm) {
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
        McpProviderStatusLogId entity = (McpProviderStatusLogId) o;
        return Objects.equals(this.providerInfoId, entity.providerInfoId) &&
                Objects.equals(this.actionDtm, entity.actionDtm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerInfoId, actionDtm);
    }
}