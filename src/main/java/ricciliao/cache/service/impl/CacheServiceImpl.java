package ricciliao.cache.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.cache.component.CacheProviderSelector;
import ricciliao.cache.pojo.ProviderCache;
import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.cache.service.CacheService;
import ricciliao.x.cache.pojo.ConsumerIdentifier;
import ricciliao.x.cache.pojo.ProviderInfo;
import ricciliao.x.cache.query.CacheBatchQuery;
import ricciliao.x.component.random.RandomGenerator;

import java.time.Instant;
import java.util.Objects;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    private CacheProviderSelector providerSelector;

    @Autowired
    public void setProviderSelector(CacheProviderSelector providerSelector) {
        this.providerSelector = providerSelector;
    }

    @Override
    public String create(ConsumerIdentifier identifier, ProviderOperation.Single single) {
        Instant now = Instant.now();
        if (Boolean.TRUE.equals(providerSelector.isStatical(identifier))) {
            single.getData().setCreatedDtm(now);
            single.getData().setUpdatedDtm(single.getData().getCreatedDtm());
        } else {
            single.getData().setCacheKey(RandomGenerator.nextString(12).allAtLeast(3).generate());
            single.getData().setCreatedDtm(now);
            single.getData().setUpdatedDtm(now);
        }
        providerSelector.selectProvider(identifier).create(single);

        return single.getData().getCacheKey();
    }

    @Override
    public boolean update(ConsumerIdentifier identifier, ProviderOperation.Single single) {
        ProviderOperation.Single existing = this.get(identifier, single.getData().getCacheKey());
        if (Objects.isNull(existing.getData())) {

            return false;
        }
        single.getData().setCreatedDtm(existing.getData().getCreatedDtm());
        if (Boolean.FALSE.equals(providerSelector.isStatical(identifier))) {
            single.getData().setUpdatedDtm(Instant.now());
        }

        return providerSelector.selectProvider(identifier).update(single);
    }

    @Override
    public boolean delete(ConsumerIdentifier identifier, String id) {
        ProviderOperation.Single single = this.get(identifier, id);
        if (Objects.isNull(single.getData())) {

            return false;
        }

        return providerSelector.selectProvider(identifier).delete(id);
    }

    @Override
    public ProviderOperation.Single get(ConsumerIdentifier identifier, String id) {

        return providerSelector.selectProvider(identifier).get(id);
    }

    @Override
    public ProviderOperation.Batch list(ConsumerIdentifier identifier, CacheBatchQuery query) {

        return providerSelector.selectProvider(identifier).list(query);
    }

    @Override
    public boolean delete(ConsumerIdentifier identifier, CacheBatchQuery query) {

        return providerSelector.selectProvider(identifier).delete(query);
    }

    @Override
    public ProviderInfo providerInfo(ConsumerIdentifier identifier) {

        return providerSelector.selectProvider(identifier).getProviderInfo();
    }

    @Override
    public boolean create(ConsumerIdentifier identifier, ProviderOperation.Batch batch) {
        Instant now = Instant.now();
        if (Boolean.TRUE.equals(providerSelector.isStatical(identifier))) {
            for (ProviderCache cache : batch.getData()) {
                cache.setCreatedDtm(now);
                cache.setUpdatedDtm(cache.getCreatedDtm());
            }
        } else {
            for (ProviderCache cache : batch.getData()) {
                cache.setCacheKey(RandomGenerator.nextString(12).allAtLeast(3).generate());
                cache.setCreatedDtm(now);
                cache.setUpdatedDtm(now);
            }
        }

        return providerSelector.selectProvider(identifier).create(batch);
    }

}
