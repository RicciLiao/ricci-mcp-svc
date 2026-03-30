package ricciliao.mcp.pojo.dto;

import ricciliao.x.component.payload.PayloadData;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

public class McpProviderInfoDto implements PayloadData {
    @Serial
    private static final long serialVersionUID = -8478330643935559387L;

    private Long id;
    private String consumer;
    private String store;
    private Long provider;
    private Long ttlSeconds;
    private Boolean active;
    private Boolean statical;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Long version;
    private McpProviderPassInfoDto passInfo;
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public McpProviderPassInfoDto getPassInfo() {
        return passInfo;
    }

    public void setPassInfo(McpProviderPassInfoDto passInfo) {
        this.passInfo = passInfo;
    }

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderInfoDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getConsumer(), that.getConsumer()) && Objects.equals(getStore(), that.getStore()) && Objects.equals(getProvider(), that.getProvider()) && Objects.equals(getTtlSeconds(), that.getTtlSeconds()) && Objects.equals(getActive(), that.getActive()) && Objects.equals(getStatical(), that.getStatical()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getPassInfo(), that.getPassInfo()) && Objects.equals(getDeleted(), that.getDeleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getActive(), getStatical(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getPassInfo(), getDeleted());
    }
}