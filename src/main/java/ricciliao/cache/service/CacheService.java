package ricciliao.cache.service;


import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.x.cache.pojo.ProviderInfo;
import ricciliao.x.cache.pojo.StoreIdentifier;
import ricciliao.x.cache.query.CacheBatchQuery;

public interface CacheService {

    String create(StoreIdentifier identifier,
                  ProviderOperation.Single single);

    boolean update(StoreIdentifier identifier,
                   ProviderOperation.Single single);

    boolean delete(StoreIdentifier identifier, String id);

    ProviderOperation.Single get(StoreIdentifier identifier, String id);

    ProviderOperation.Batch list(StoreIdentifier identifier, CacheBatchQuery query);

    boolean delete(StoreIdentifier identifier, CacheBatchQuery query);

    ProviderInfo providerInfo(StoreIdentifier identifier);

    boolean create(StoreIdentifier identifier,
                   ProviderOperation.Batch batch);

}
