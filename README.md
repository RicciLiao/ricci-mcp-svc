# Cache Store CURD Interface Service

## *Cache Provider `🚀️ V1.0.0`*

### 📚 Dependency

Please refer to `dependencies-control-center` for the version number.

| groupId                  | artifactId                          | scope   |
|--------------------------|-------------------------------------|---------|
| org.springframework.boot | spring-boot-starter-web             | compile |
| org.springdoc            | springdoc-openapi-starter-webmvc-ui | compile |
| org.springframework.boot | spring-boot-starter-data-mongodb    | compile |
| org.springframework.boot | spring-boot-starter-validation      | compile |
| redis.clients            | jedis                               | compile |
| ricciliao.x              | components-starter                  | compile |
| ricciliao.x              | cache-common-component              | compile |
| jakarta.servlet          | jakarta.servlet-api                 | compile |

### 📌 Usage

**Cache Provider** provides a series of universal RESTful interfaces for MongoDB and Redis,
you can choose MongoDB or Redis as your provider(s) for your data,
also, you can use more than one provider(s) at the same times if you need to cache dataA into MongoDB and dataB into
Redis,
and don`t worry about the implement, just define it and use it!

### 📝Configuration

The **Cache Provider** include a custom starter which base on spring starter, you can config the provider(s) properties
in
your `application.yml`
and the starter will auto define provider(s) by your properties when your app start up.

```yaml
cache-provider:
  redis:
    consumer-list:
      - consumer:
        store-list:
          - store:
            host:
            port:
            password:
            database:
            addition:
              max-idle:
              max-total:
              min-idle:
              timeout:
              ttl:
              statical:
  mongo:
    consumer-list:
      - consumer:
        store-list:
          - store:
            host:
            port:
            password:
            database:
            addition:
              ttl:
          - store:
            host:
            port:
            password:
            database:
            store-class-name:
            addition:
              timeout:
              statical:
```

Obviously, If you use the properties which starting with `redis` ,
the **Cache Provider** will define Redis to store your data,
and use `mongo`, will define MongoDB.

As we know, the definition of starter properties are determined by the POJO class.

* #### ProviderCacheProperties.class

```java
  public abstract static class ProviderProperties {
    private String consumer;

    public abstract List<? extends StoreProperties> getStoreList();
}

public abstract static class StoreProperties {
    private String store = "";
    private String host;
    private Integer port;
    private String password;
    private String database;
    private Class<CacheDto> storeClassName;

    public abstract AdditionalProperties getAddition();
}

public abstract static class AdditionalProperties {
    private Duration timeout = Duration.ofSeconds(30);
    private Duration ttl = Duration.ofSeconds(60);
    private Boolean statical = false;
}
```

* #### ProviderProperties.class

    * `consumer`: define the identity code of service which will use this provider,
      like A service use A as code, B service as B.
* #### StoreProperties.class

    * `store`: define the identity code of data which from the consumer.
    * `host`: MongoDB or Redis host.
    * `port`: MongoDB or Redis port.
    * `password`: MongoDB or Redis password.
    * `database`: MongoDB scheme or Redis DB index.
* #### AdditionalProperties.class

    * `timeout`: connection pool timeout.
    * `ttl`: data expired time.
    * `statical`: true=static data, like some code list; false=dynamic data, which can be updated in real-time.
* #### RedisCacheAutoProperties.class

```java
public class RedisCacheAutoProperties extends ApplicationProperties {
    private List<ConsumerProperties> consumerList = new ArrayList<>();

    public static class ConsumerProperties extends ProviderCacheProperties.ProviderProperties<ConsumerProperties.StoreProperties> {
        private List<StoreProperties> storeList = new ArrayList<>();

        @Override
        public List<StoreProperties> getStoreList() {
            return storeList;
        }

        public static class StoreProperties extends ProviderCacheProperties.StoreProperties {
            private AdditionalProperties addition = new AdditionalProperties();

            @Override
            public AdditionalProperties getAddition() {
                return addition;
            }

            public static class AdditionalProperties extends ProviderCacheProperties.AdditionalProperties {
                private Integer minIdle = 2;
                private Integer maxIdle = 5;
                private Integer maxTotal = 20;
            }
        }
    }
}
```

* #### MongoCacheAutoProperties.class

```java
public class MongoCacheAutoProperties extends ApplicationProperties {
    private List<ConsumerProperties> consumerList = new ArrayList<>();

    public static class ConsumerProperties extends ProviderCacheProperties.ProviderProperties<ConsumerProperties.StoreProperties> {
        private List<StoreProperties> storeList = new ArrayList<>();

        @Override
        public List<StoreProperties> getStoreList() {
            return storeList;
        }

        public static class StoreProperties extends ProviderCacheProperties.StoreProperties {
            private AdditionalProperties addition = new AdditionalProperties();

            @Override
            public AdditionalProperties getAddition() {
                return addition;
            }

            public static class AdditionalProperties extends ProviderCacheProperties.AdditionalProperties {

            }
        }
    }
}
```

### 📝 Coding

#### 🐾 Common

*Please refer to `x-cache-components`*

* #### AbstractCacheOperation.class

```java
public abstract class AbstractCacheOperation<T extends Serializable> implements Serializable {
    private T data;

    public static class Single<T extends StoreCache<? extends Serializable>> extends AbstractCacheOperation<T> {
    }

    public static class Batch<T extends StoreCache<? extends Serializable>> extends AbstractCacheOperation<T[]> {
    }
}
```

Cache operation payload for CURD.

---

* #### StoreCache.class

```java
public class StoreCache<T extends Serializable> implements PayloadData {
    @CacheQuery.Support(CacheQuery.Property.CACHE_KEY)
    @CacheId
    private String cacheKey;
    @CacheQuery.Support(CacheQuery.Property.CREATED_DTM)
    private Instant createdDtm;
    @CacheQuery.Support(CacheQuery.Property.UPDATED_DTM)
    private Instant updatedDtm;
    private Instant ttlEffectedDtm;
    @Store
    private T store;
}
```

Definition of data structure which stored in **Cache Provider**.

---

* #### StoreIdentifier.class

```java
public class StoreIdentifier implements PayloadData {
    private String consumer;
    private String store;

}
```

The identification of cache data, please refer `📝Configuration`.

---

* #### ProviderInfo.class

```java
public class ProviderInfo {
    private Long count;
    private LocalDateTime createdDtm;
    private LocalDateTime maxUpdatedDtm;
    private ConsumerIdentifierDto consumerIdentifier;
}
```

You can retrieve a provider information from this POJO.

---

#### 📀 Provider

* #### ProviderCache.class

```java
public class ProviderCache extends StoreCache<byte[]> {
}
```

Definition of provider data structure which stored in **Cache Provider**.

---

* #### ProviderOperation.class

```java
public interface ProviderOperation {

    class Single extends AbstractCacheOperation.Single<ProviderCache> {
    }

    class Batch extends AbstractCacheOperation.Batch<ProviderCache> {
    }

}
```

Definition of provider operation payload, you can use this to manipulate single or batch data(s).

---

* #### CacheOperationConverter.class

```java
public abstract class CacheOperationConverter<T extends AbstractCacheOperation<? extends Serializable>> extends AbstractHttpMessageConverter<T> {

    protected final ObjectMapper objectMapper;
    private final CacheProviderSelector cacheProviderSelector;
    private final ResponseHttpMessageConverter responseHttpMessageConverter;

    protected CacheOperationConverter(ObjectMapper objectMapper, CacheProviderSelector cacheProviderSelector) {
        super(MediaType.APPLICATION_JSON);
        this.objectMapper = objectMapper;
        this.cacheProviderSelector = cacheProviderSelector;
        this.responseHttpMessageConverter = new ResponseHttpMessageConverter(objectMapper);
    }

    protected CacheProvider verify(HttpInputMessage inputMessage) throws ParameterException {
        List<String> customer = inputMessage.getHeaders().get(XCacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER);
        List<String> store = inputMessage.getHeaders().get(XCacheConstants.HTTP_HEADER_FOR_CACHE_STORE);
        if (CollectionUtils.isEmpty(customer) || customer.size() > 1
                || CollectionUtils.isEmpty(store) || store.size() > 1) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }
        StoreIdentifier identifier = new StoreIdentifier(customer.getFirst(), store.getFirst());
        if (!cacheProviderSelector.getCacheProviderMap().containsKey(identifier)) {

            throw new ParameterException(SecondaryCodeEnum.BLANK);
        }

        return cacheProviderSelector.getCacheProviderMap().get(identifier);
    }

    protected ProviderCache encode(JsonNode jsonNode, CacheProvider cacheProvider) throws JsonProcessingException {
        StoreCache<Serializable> store = objectMapper.convertValue(jsonNode, new TypeReference<>() {
        });
        String data = objectMapper.writeValueAsString(store.getStore());
        store.setStore(objectMapper.writeValueAsBytes(data));
        Instant now = Instant.now();
        if (jsonNode.hasNonNull("ttlSec")) {
            long ttlDiff = jsonNode.get("ttlSec").asLong() - cacheProvider.getStoreProps().getAddition().getTtl().getSeconds();
            store.setTtlEffectedDtm(now.plusSeconds(ttlDiff));
        } else {
            store.setTtlEffectedDtm(now);
        }

        return objectMapper.convertValue(store, new TypeReference<>() {
        });
    }

    protected StoreCache<Serializable> decode(ProviderCache cache) throws IOException {
        StoreCache<Serializable> result = objectMapper.convertValue(cache, new TypeReference<>() {
        });
        String data = objectMapper.readValue(cache.getStore(), String.class);
        result.setStore(objectMapper.readValue(data, new TypeReference<LinkedHashMap<String, Serializable>>() {
        }));

        return result;
    }

    @Nonnull
    @Override
    protected final T readInternal(@Nonnull Class<? extends T> clazz, @Nonnull HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
        try {
            JsonNode jsonNode = objectMapper.readTree(inputMessage.getBody());

            return this.readInternal(jsonNode.get("data"), this.verify(inputMessage));
        } catch (Exception e) {

            throw new HttpMessageNotReadableException(e.getMessage(), inputMessage);
        }
    }

    @Override
    protected final void writeInternal(@Nonnull T t, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Response<PayloadData> result;
        if (Objects.isNull(t.getData())) {
            result = ResponseUtils.success();
        } else {
            result = ResponseUtils.success(this.writeInternal(t.getData()));
        }
        responseHttpMessageConverter.write(result, null, this.getSupportedMediaTypes().getFirst(), outputMessage);
    }

    @Nonnull
    abstract T readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws HttpMessageNotReadableException, JsonProcessingException;

    @Nonnull
    abstract PayloadData writeInternal(@Nonnull Serializable data) throws IOException;

}
```

---

* #### ProviderOpSingleConverter.class

```java
public class ProviderOpSingleConverter extends CacheOperationConverter<ProviderOperation.Single> {

    public ProviderOpSingleConverter(ObjectMapper objectMapper,
                                     CacheProviderSelector cacheProvider) {
        super(objectMapper, cacheProvider);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return ProviderOperation.Single.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    ProviderOperation.Single readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws JsonProcessingException {

        return ProviderOperation.of(this.encode(node, cacheProvider));
    }

    @Nonnull
    @Override
    PayloadData writeInternal(@Nonnull Serializable data) throws IOException {

        return this.decode((ProviderCache) data);
    }

}
```

---

* #### ProviderOpBatchConverter.class

```java
public class ProviderOpBatchConverter extends CacheOperationConverter<ProviderOperation.Batch> {

    public ProviderOpBatchConverter(ObjectMapper objectMapper,
                                    CacheProviderSelector cacheProviderSelector) {
        super(objectMapper, cacheProviderSelector);
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return ProviderOperation.Batch.class.isAssignableFrom(clazz);
    }

    @Nonnull
    @Override
    ProviderOperation.Batch readInternal(@Nonnull JsonNode node, CacheProvider cacheProvider) throws JsonProcessingException {
        ArrayNode arrayNode = (ArrayNode) node;
        ProviderCache[] stores = new ProviderCache[arrayNode.size()];
        for (int i = 0; i < arrayNode.size(); i++) {
            stores[i] = objectMapper.convertValue(this.encode(arrayNode.get(i), cacheProvider), new TypeReference<>() {
            });
        }

        return ProviderOperation.of(stores);
    }

    @Nonnull
    @Override
    PayloadData writeInternal(@Nonnull Serializable data) {

        return SimpleData.of(
                Arrays.stream((ProviderCache[]) data)
                        .map(store -> SneakyThrowUtils.get(() -> this.decode(store))).toList()
        );
    }

}
```

---

#### 💿 Consumer

* #### ConsumerStore.class

```java
public interface ConsumerStore extends PayloadData {

    String generateCacheKey();

}
```

Interface of cache data, if you want to cache your data in **Cache Provider**, please implement this in your POJO.

---

* #### ConsumerCache.class

```java
public class ConsumerCache<T extends ConsumerStore> extends StoreCache<T> implements Serializable {
    private Long ttlSec;
}
```

Definition of consumer data structure.

---

* #### ConsumerOperation.class

```java
public interface ConsumerOperation {

    class Single<T extends ConsumerStore> extends AbstractCacheOperation.Single<ConsumerCache<T>> {
    }

    class Batch<T extends ConsumerStore> extends AbstractCacheOperation.Batch<ConsumerCache<T>> {
    }

}
```

Definition of consumer operation payload, you can use this to manipulate single or batch data(s).

---

* #### CacheBatchQuery.class

```java
public class CacheBatchQuery implements Serializable {
    private Long limit;
    private CacheQuery.Property sortBy;
    private CacheQuery.Sort.Direction sortDirection;
    private Map<CacheQuery.Property, Serializable> criteriaMap = new EnumMap<>(CacheQuery.Property.class);
}
```

You can use this POJO to retrieve a list of data by your criteria in consumer.

---

* ### 🎯 Interface

    * *POST* `/operation`
      Create a new record for the consumer(with identifier).
    * *PUT* `/operation`
      Update a existed record for the consumer(with identifier).
    * *DELETE* `/operation/{id}`
      Delete a existed record for the consumer(with identifier).
    * *GET* `/operation/{id}`
      Retrieve a existed record for the consumer(with identifier).
    * *POST* `/operation/batch`
      Batch create new records for the consumer(with identifier).
    * *POST* `/operation/list`
      Retrieve list of existed record for the consumer(with identifier).
    * *GET* `/operation/extra/providerInfo`
      Retrieve provider information for the consumer(with identifier).

---

* ### 💱 Workflow

    1.
        * *POST* `/operation`
        * *PUT* `/operation`
        * *POST* `/operation/batch`
          ![saving.png](assets/saving.png)
    2.
        * *GET* `/operation/{id}`
        * *POST* `/operation/list`
          ![retrieval.png](assets/retrieval.png)

---

🤖 Good luck and enjoy it ~~
