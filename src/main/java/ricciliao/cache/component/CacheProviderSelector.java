package ricciliao.cache.component;


import jakarta.annotation.Nullable;
import ricciliao.x.cache.pojo.StoreIdentifier;

import java.util.HashMap;
import java.util.Map;

public class CacheProviderSelector {

    private final Map<StoreIdentifier, CacheProvider> cacheProviderMap;
    private final Map<StoreIdentifier, Boolean> cacheStaticalMap;

    public CacheProviderSelector() {
        this.cacheProviderMap = new HashMap<>();
        this.cacheStaticalMap = new HashMap<>();
    }

    public Map<StoreIdentifier, CacheProvider> getCacheProviderMap() {
        return cacheProviderMap;
    }

    public Map<StoreIdentifier, Boolean> getCacheStaticalMap() {
        return cacheStaticalMap;
    }

    public CacheProvider selectProvider(@Nullable StoreIdentifier identifier) {

        return getCacheProviderMap().get(identifier);
    }

    public Boolean isStatical(@Nullable StoreIdentifier identifier) {

        return getCacheStaticalMap().get(identifier);
    }

}
