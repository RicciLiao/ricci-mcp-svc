package ricciliao.mcp.pojo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class McpProviderPassInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -7737771839883533097L;

    private Long providerInfoId;
    private String passKey;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Instant version;

    public Long getProviderInfoId() {
        return providerInfoId;
    }

    public void setProviderInfoId(Long providerInfoId) {
        this.providerInfoId = providerInfoId;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
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
        if (!(o instanceof McpProviderPassInfoDto that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getPassKey(), that.getPassKey()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getPassKey(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}