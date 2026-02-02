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
@Table(name = "mcp_provider_info_log", schema = "mcp")
@IdClass(McpProviderInfoLogPoId.class)
public class McpProviderInfoLogPo implements LoggerPo {
    @Serial
    private static final long serialVersionUID = 4911198406548894776L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "action_dtm", nullable = false)
    private Instant actionDtm;

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

    @Column(name = "version", nullable = false)
    private Instant version;

    @Column(name = "action_cd", nullable = false, length = 1)
    private Character actionCd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Instant getActionDtm() {
        return actionDtm;
    }

    @Override
    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
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
        if (!(o instanceof McpProviderInfoLogPo logPo)) return false;
        return Objects.equals(getId(), logPo.getId()) && Objects.equals(getActionDtm(), logPo.getActionDtm()) && Objects.equals(getConsumer(), logPo.getConsumer()) && Objects.equals(getStore(), logPo.getStore()) && Objects.equals(getProvider(), logPo.getProvider()) && Objects.equals(getTtlSeconds(), logPo.getTtlSeconds()) && Objects.equals(getIsActive(), logPo.getIsActive()) && Objects.equals(getIsStatic(), logPo.getIsStatic()) && Objects.equals(getCreatedDtm(), logPo.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), logPo.getUpdatedDtm()) && Objects.equals(getVersion(), logPo.getVersion()) && Objects.equals(getActionCd(), logPo.getActionCd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActionDtm(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getIsActive(), getIsStatic(), getCreatedDtm(), getUpdatedDtm(), getVersion(), getActionCd());
    }
}