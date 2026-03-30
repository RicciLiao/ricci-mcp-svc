package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_pass_info")
public class McpProviderPassInfoPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -578392035406690095L;

    @Id
    @Column(name = "provider_info_id")
    private Long providerInfoId;

    @Column(name = "pass_key")
    private String passKey;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_dtm")
    private Instant createdDtm;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_dtm")
    private Instant updatedDtm;

    @Version
    @Column(name = "version")
    private Long version;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderPassInfoPo po)) return false;
        return Objects.equals(getProviderInfoId(), po.getProviderInfoId()) && Objects.equals(getPassKey(), po.getPassKey()) && Objects.equals(getCreatedBy(), po.getCreatedBy()) && Objects.equals(getCreatedDtm(), po.getCreatedDtm()) && Objects.equals(getUpdatedBy(), po.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), po.getUpdatedDtm()) && Objects.equals(getVersion(), po.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getPassKey(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}