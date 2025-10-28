package ricciliao.cache.service;


import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.x.cache.pojo.ConsumerIdentifier;
import ricciliao.x.cache.pojo.ProviderInfo;
import ricciliao.x.cache.query.CacheBatchQuery;

public interface CacheService {

    String create(ConsumerIdentifier identifier,
                  ProviderOperation.Single single);

    boolean update(ConsumerIdentifier identifier,
                   ProviderOperation.Single single);

    boolean delete(ConsumerIdentifier identifier, String id);

    ProviderOperation.Single get(ConsumerIdentifier identifier, String id);

    ProviderOperation.Batch list(ConsumerIdentifier identifier, CacheBatchQuery query);

    boolean delete(ConsumerIdentifier identifier, CacheBatchQuery query);

    ProviderInfo providerInfo(ConsumerIdentifier identifier);

    boolean create(ConsumerIdentifier identifier,
                   ProviderOperation.Batch batch);

}
