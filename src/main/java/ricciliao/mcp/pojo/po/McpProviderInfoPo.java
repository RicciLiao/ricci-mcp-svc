package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_info", schema = "mcp")
public class McpProviderInfoPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2014559219143099725L;

    public McpProviderInfoPo() {
    }

    public McpProviderInfoPo(McpProviderInfoPo po,
                             McpProviderPassInfoPo passInfo) {
        this.id = po.id;
        this.consumer = po.consumer;
        this.provider = po.provider;
        this.ttlSeconds = po.ttlSeconds;
        this.isActive = po.isActive;
        this.isStatic = po.isStatic;
        this.createdDtm = po.createdDtm;
        this.updatedDtm = po.updatedDtm;
        this.version = po.version;
        this.passInfo = passInfo;
    }

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "consumer", nullable = false, length = 25)
    private String consumer;

    @Column(name = "store", nullable = false, length = 25)
    private String store;

    @Column(name = "provider", nullable = false)
    private Long provider;

    @Column(name = "ttl_seconds")
    private Long ttlSeconds;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "is_static", nullable = false)
    private Boolean isStatic;

    @Column(name = "created_dtm", nullable = false)
    private Instant createdDtm;

    @Column(name = "updated_dtm", nullable = false)
    private Instant updatedDtm;

    @Version
    @Column(name = "version", nullable = false)
    private Instant version;

    @Transient
    private transient McpProviderPassInfoPo passInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Long getProvider() {
        return provider;
    }

    public void setProvider(Long provider) {
        this.provider = provider;
    }

    public Long getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(Long ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(Boolean isStatic) {
        this.isStatic = isStatic;
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

    public McpProviderPassInfoPo getPassInfo() {
        return passInfo;
    }

    public void setPassInfo(McpProviderPassInfoPo passInfo) {
        this.passInfo = passInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderInfoPo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConsumer(), that.getConsumer()) && Objects.equals(getStore(), that.getStore()) && Objects.equals(getProvider(), that.getProvider()) && Objects.equals(getTtlSeconds(), that.getTtlSeconds()) && Objects.equals(getIsActive(), that.getIsActive()) && Objects.equals(getIsStatic(), that.getIsStatic()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getIsActive(), getIsStatic(), getCreatedDtm(), getUpdatedDtm(), getVersion());
    }
}