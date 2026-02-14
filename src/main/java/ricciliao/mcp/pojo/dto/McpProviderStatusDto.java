package ricciliao.mcp.pojo.dto;

import ricciliao.x.component.persistence.ModifiablePo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

public class McpProviderStatusDto implements ModifiablePo {
    @Serial
    private static final long serialVersionUID = 1246108923365644957L;

    private Long providerInfoId;
    private Boolean status;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
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
        if (!(o instanceof McpProviderStatusDto that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getStatus(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}