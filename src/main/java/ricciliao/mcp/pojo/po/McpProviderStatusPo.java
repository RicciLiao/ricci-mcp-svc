package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import ricciliao.x.component.persistence.ModifiablePo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_status", schema = "mcp")
public class McpProviderStatusPo implements ModifiablePo {
    @Serial
    private static final long serialVersionUID = -8464410183770097779L;

    @Id
    @Column(name = "provider_info_id", nullable = false)
    private Long providerInfoId;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_dtm", nullable = false)
    private Instant createdDtm;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name = "updated_dtm", nullable = false)
    private Instant updatedDtm;

    @Version
    @Column(name = "version", nullable = false)
    private Instant version;

    public Long getProviderInfoId() {
        return providerInfoId;
    }

    public void setProviderInfoId(Long id) {
        this.providerInfoId = id;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderStatusPo that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getStatus(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}