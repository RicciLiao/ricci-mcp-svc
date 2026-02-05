package ricciliao.mcp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.mcp.service.CacheOperationService;
import ricciliao.x.component.random.RandomGenerator;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpQuery;

import java.time.Instant;
import java.util.Objects;

@Service("cacheOperationService")
public class CacheOperationServiceImpl implements CacheOperationService {

    private McpProviderRegistry mcpProviderRegistry;

    @Autowired
    public void setMcpProviderRegistry(McpProviderRegistry mcpProviderRegistry) {
        this.mcpProviderRegistry = mcpProviderRegistry;
    }

    @Override
    public String create(McpIdentifier identifier, AbstractProviderCacheMessage.Single single) {
        Instant now = Instant.now();
        single.getData().setCreatedDtm(now);
        single.getData().setUpdatedDtm(now);
        if (!Boolean.TRUE.equals(mcpProviderRegistry.isStatic(identifier))) {
            single.getData().setUid(RandomGenerator.nextString(12).allAtLeast(3).generate());
        }
        mcpProviderRegistry.get(identifier).create(single);

        return single.getData().getUid();
    }

    @Override
    public boolean update(McpIdentifier identifier, AbstractProviderCacheMessage.Single single) {
        AbstractProviderCacheMessage.Single existing = this.get(identifier, single.getData().getUid());
        if (Objects.isNull(existing.getData())) {

            return false;
        }
        existing.getData().setData(single.getData().getData());
        if (Boolean.FALSE.equals(mcpProviderRegistry.isStatic(identifier))) {
            existing.getData().setUpdatedDtm(Instant.now());
        }

        return mcpProviderRegistry.get(identifier).update(existing);
    }

    @Override
    public AbstractProviderCacheMessage.Single get(McpIdentifier identifier, String id) {

        return mcpProviderRegistry.get(identifier).get(id);
    }

    @Override
    public boolean delete(McpIdentifier identifier, String id) {
        AbstractProviderCacheMessage.Single single = this.get(identifier, id);
        if (Objects.isNull(single.getData())) {

            return false;
        }

        return mcpProviderRegistry.get(identifier).delete(id);
    }


    @Override
    public boolean create(McpIdentifier identifier, AbstractProviderCacheMessage.Batch batch) {
        Instant now = Instant.now();
        if (Boolean.TRUE.equals(mcpProviderRegistry.isStatic(identifier))) {
            for (ProviderCache cache : batch.getData()) {
                cache.setCreatedDtm(now);
                cache.setUpdatedDtm(now);
            }
        } else {
            for (ProviderCache cache : batch.getData()) {
                cache.setUid(RandomGenerator.nextString(12).allAtLeast(3).generate());
                cache.setCreatedDtm(now);
                cache.setUpdatedDtm(now);
            }
        }

        return mcpProviderRegistry.get(identifier).create(batch);
    }

    @Override
    public boolean update(McpIdentifier identifier, AbstractProviderCacheMessage.Batch batch) {
        ProviderCache[] existings = new ProviderCache[batch.getData().length];
        for (int i = 0; i < batch.getData().length; i++) {
            ProviderCache existing = this.get(identifier, batch.getData()[i].getUid()).getData();
            if (Objects.isNull(existing)) {

                return false;
            }
            existings[i] = existing;
            existing.setData(batch.getData()[i].getData());
            if (Boolean.FALSE.equals(mcpProviderRegistry.isStatic(identifier))) {
                existing.setUpdatedDtm(Instant.now());
            }
        }

        return mcpProviderRegistry.get(identifier).update(AbstractProviderCacheMessage.of(existings));
    }

    @Override
    public AbstractProviderCacheMessage.Batch list(McpIdentifier identifier, McpQuery query) {

        return mcpProviderRegistry.get(identifier).list(query);
    }

    @Override
    public boolean delete(McpIdentifier identifier, McpQuery query) {

        return mcpProviderRegistry.get(identifier).delete(query);
    }

    @Override
    public McpProviderInfo info(McpIdentifier identifier) {

        return mcpProviderRegistry.get(identifier).info();
    }

}
