package ricciliao.mcp.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LoggerPo;

import java.io.Serial;
import java.time.Instant;

@Entity
@Table(name = "mcp_provider_info_log", schema = "mcp")
@IdClass(McpProviderInfoLogPoId.class)
public class McpProviderInfoLogPo implements LoggerPo {
    @Serial
    private static final long serialVersionUID = -6948097405911012909L;

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

    @Override
    public Character getActionCd() {
        return actionCd;
    }

    @Override
    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

}