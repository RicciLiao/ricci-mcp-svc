package ricciliao.mcp.pojo.po;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class McpProviderInfoLogPoId implements Serializable {
    @Serial
    private static final long serialVersionUID = -503283456076092163L;

    private Long id;
    private Instant actionDtm;

    public McpProviderInfoLogPoId() {
    }

    public McpProviderInfoLogPoId(Long id, Instant actionDtm) {
        this.id = id;
        this.actionDtm = actionDtm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        McpProviderInfoLogPoId entity = (McpProviderInfoLogPoId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.actionDtm, entity.actionDtm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actionDtm);
    }
}