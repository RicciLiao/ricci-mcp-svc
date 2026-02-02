package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LoggerPo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_pass_info_log", schema = "mcp")
@IdClass(McpProviderPassInfoLogPoId.class)
public class McpProviderPassInfoLogPo implements LoggerPo {
    @Serial
    private static final long serialVersionUID = 3306243738243137271L;

    @Id
    @Column(name = "provider_info_id", nullable = false)
    private Long providerInfoId;

    @Id
    @Column(name = "action_dtm", nullable = false)
    private Instant actionDtm;

    @Column(name = "pass_key", nullable = false, length = 16)
    private String passKey;

    @Column(name = "created_dtm", nullable = false)
    private Instant createdDtm;

    @Column(name = "updated_dtm", nullable = false)
    private Instant updatedDtm;

    @Column(name = "version", nullable = false)
    private Instant version;

    @Column(name = "action_cd", nullable = false, length = 1)
    private Character actionCd;

    public Long getProviderInfoId() {
        return providerInfoId;
    }

    public void setProviderInfoId(Long providerInfoId) {
        this.providerInfoId = providerInfoId;
    }

    @Override
    public Instant getActionDtm() {
        return actionDtm;
    }

    @Override
    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Instant createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Instant getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Instant updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public Instant getVersion() {
        return version;
    }

    public void setVersion(Instant version) {
        this.version = version;
    }

    @Override
    public Character getActionCd() {
        return actionCd;
    }

    @Override
    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderPassInfoLogPo that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getActionDtm(), that.getActionDtm()) && Objects.equals(getPassKey(), that.getPassKey()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getActionCd(), that.getActionCd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getActionDtm(), getPassKey(), getCreatedDtm(), getUpdatedDtm(), getVersion(), getActionCd());
    }
}