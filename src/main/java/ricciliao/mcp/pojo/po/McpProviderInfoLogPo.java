package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LogEntity;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "mcp_provider_info_log")
@IdClass(McpProviderInfoLogId.class)
public class McpProviderInfoLogPo implements LogEntity {
    @Serial
    private static final long serialVersionUID = 2490126725374426060L;

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "action_dtm")
    private Instant actionDtm;

    @Column(name = "consumer")
    private String consumer;

    @Column(name = "store")
    private String store;

    @Column(name = "provider")
    private Long provider;

    @Column(name = "ttl_seconds")
    private Long ttlSeconds;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "statical")
    private Boolean statical;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_dtm")
    private Instant createdDtm;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_dtm")
    private Instant updatedDtm;

    @Column(name = "version")
    private Long version;

    @Column(name = "action_cd", length = 1)
    private Character actionCd;

    @Column(name = "action_by")
    private Long actionBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getActionDtm() {
        return actionDtm;
    }

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
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
        if (!(o instanceof McpProviderInfoLogPo logPo)) return false;
        return Objects.equals(getId(), logPo.getId()) && Objects.equals(getActionDtm(), logPo.getActionDtm()) && Objects.equals(getConsumer(), logPo.getConsumer()) && Objects.equals(getStore(), logPo.getStore()) && Objects.equals(getProvider(), logPo.getProvider()) && Objects.equals(getTtlSeconds(), logPo.getTtlSeconds()) && Objects.equals(getActive(), logPo.getActive()) && Objects.equals(getStatical(), logPo.getStatical()) && Objects.equals(getCreatedBy(), logPo.getCreatedBy()) && Objects.equals(getCreatedDtm(), logPo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), logPo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), logPo.getUpdatedDtm()) && Objects.equals(getVersion(), logPo.getVersion()) && Objects.equals(getActionCd(), logPo.getActionCd()) && Objects.equals(getActionBy(), logPo.getActionBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActionDtm(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getActive(), getStatical(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getActionCd(), getActionBy());
    }
}