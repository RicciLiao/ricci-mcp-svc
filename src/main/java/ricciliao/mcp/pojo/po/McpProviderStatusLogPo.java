package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LogPo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_status_log")
@IdClass(McpProviderStatusLogId.class)
public class McpProviderStatusLogPo implements LogPo {
    @Serial
    private static final long serialVersionUID = 8255375866474570315L;

    @Id
    @Column(name = "provider_info_id")
    private Long providerInfoId;

    @Id
    @Column(name = "action_dtm")
    private Instant actionDtm;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_dtm")
    private Instant createdDtm;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_dtm")
    private Instant updatedDtm;

    @Column(name = "version")
    private Instant version;

    @Column(name = "action_cd")
    private Character actionCd;

    @Column(name = "action_by")
    private Long actionBy;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Instant createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
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

    public Character getActionCd() {
        return actionCd;
    }

    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

    public Long getActionBy() {
        return actionBy;
    }

    public void setActionBy(Long actionBy) {
        this.actionBy = actionBy;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderStatusLogPo that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getActionDtm(), that.getActionDtm()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getActionCd(), that.getActionCd()) && Objects.equals(getActionBy(), that.getActionBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getActionDtm(), getStatus(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getActionCd(), getActionBy());
    }
}