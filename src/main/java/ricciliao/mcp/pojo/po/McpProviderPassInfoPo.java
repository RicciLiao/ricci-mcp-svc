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
@Table(name = "mcp_provider_pass_info", schema = "mcp")
public class McpProviderPassInfoPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -8197076321090607199L;

    @Id
    @Column(name = "provider_info_id", nullable = false)
    private Long providerInfoId;

    @Column(name = "pass_key", nullable = false, length = 16)
    private String passKey;

    @Column(name = "created_dtm", nullable = false)
    private Instant createdDtm;

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
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderPassInfoPo that)) return false;
        return Objects.equals(getProviderInfoId(), that.getProviderInfoId()) && Objects.equals(getPassKey(), that.getPassKey()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderInfoId(), getPassKey(), getCreatedDtm(), getUpdatedDtm(), getVersion());
    }
}