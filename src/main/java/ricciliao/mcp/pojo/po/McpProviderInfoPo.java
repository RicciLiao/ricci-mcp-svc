package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import ricciliao.x.component.persistence.ModifiablePo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_info")
public class McpProviderInfoPo implements ModifiablePo {
    @Serial
    private static final long serialVersionUID = 5876055436800486469L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "statical", nullable = false)
    private Boolean statical;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatical() {
        return statical;
    }

    public void setStatical(Boolean statical) {
        this.statical = statical;
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
        if (!(o instanceof McpProviderInfoPo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConsumer(), that.getConsumer()) && Objects.equals(getStore(), that.getStore()) && Objects.equals(getProvider(), that.getProvider()) && Objects.equals(getTtlSeconds(), that.getTtlSeconds()) && Objects.equals(getActive(), that.getActive()) && Objects.equals(getStatical(), that.getStatical()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getActive(), getStatical(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}