package ricciliao.mcp.pojo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class McpProviderInfoDto implements Serializable {
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
    private Instant version;
    private McpProviderPassInfoDto passInfo;
    private McpProviderStatusDto status;

    public McpProviderStatusDto getStatus() {
        return status;
    }

    public void setStatus(McpProviderStatusDto status) {
        this.status = status;
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

    public Instant getVersion() {
        return version;
    }

    public void setVersion(Instant version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof McpProviderInfoDto dto)) return false;
        return Objects.equals(getId(), dto.getId()) && Objects.equals(getConsumer(), dto.getConsumer()) && Objects.equals(getStore(), dto.getStore()) && Objects.equals(getProvider(), dto.getProvider()) && Objects.equals(getTtlSeconds(), dto.getTtlSeconds()) && Objects.equals(getActive(), dto.getActive()) && Objects.equals(getStatical(), dto.getStatical()) && Objects.equals(getCreatedBy(), dto.getCreatedBy()) && Objects.equals(getCreatedDtm(), dto.getCreatedDtm()) && Objects.equals(getUpdatedBy(), dto.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), dto.getUpdatedDtm()) && Objects.equals(getVersion(), dto.getVersion()) && Objects.equals(getPassInfo(), dto.getPassInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConsumer(), getStore(), getProvider(), getTtlSeconds(), getActive(), getStatical(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getPassInfo());
    }
}