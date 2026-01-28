package ricciliao.mcp.provider.impl.mongo;


import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import ricciliao.mcp.common.McpConstants;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.pojo.ProviderCacheMessage;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpCriteria;
import ricciliao.x.mcp.query.McpQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MongoProvider extends AbstractMcpProvider {

    private final MongoClient mongoClient;
    private final MongoCollection<ProviderCache> mongoCollection;

    public MongoProvider(@Nonnull McpProviderInfoPo po,
                         @Nonnull MongoClient mongoClient) {
        super(po);
        this.mongoClient = mongoClient;
        this.mongoCollection = mongoClient.getDatabase(super.getIdentifier().getConsumer()).getCollection(super.getIdentifier().getStore(), ProviderCache.class);
    }

    @Override
    public boolean create(ProviderCacheMessage.Single single) {

        return Objects.nonNull(this.mongoCollection.insertOne(single.getData()).getInsertedId());
    }

    @Override
    public boolean update(ProviderCacheMessage.Single single) {
        UpdateResult result =
                this.mongoCollection.replaceOne(Filters.eq(super.getPropertyFieldName(McpCriteria.Property.ID), single.getData().getUid()), single.getData());

        return result.getModifiedCount() == 1;
    }

    @Nonnull
    @Override
    public ProviderCacheMessage.Single get(String id) {

        return ProviderCacheMessage.of(this.mongoCollection.find(Filters.eq(super.getPropertyFieldName(McpCriteria.Property.ID), id)).first());
    }

    @Override
    public boolean delete(String id) {
        DeleteResult result = this.mongoCollection.deleteOne(Filters.eq(super.getPropertyFieldName(McpCriteria.Property.ID), id));

        return result.getDeletedCount() == 1;
    }

    @Override
    public boolean create(ProviderCacheMessage.Batch batch) {
        InsertManyResult result = this.mongoCollection.insertMany(Arrays.asList(batch.getData()));

        return result.getInsertedIds().size() == batch.getData().length;
    }

    @Override
    public boolean update(ProviderCacheMessage.Batch batch) {
        List<WriteModel<ProviderCache>> bulkOperations =
                Arrays.stream(batch.getData())
                        .map(cache -> new ReplaceOneModel<>(Filters.eq(super.getPropertyFieldName(McpCriteria.Property.ID), cache.getUid()), cache))
                        .collect(Collectors.toList());
        BulkWriteResult result = this.mongoCollection.bulkWrite(bulkOperations);

        return result.getModifiedCount() == batch.getData().length;
    }

    @Nonnull
    @Override
    public ProviderCacheMessage.Batch list(McpQuery query) {
        List<ProviderCache> data =
                this.mongoCollection
                        .find(this.convert2Filter(query))
                        .sort(this.convert2Sort(query))
                        .limit(convert2Limit(query))
                        .into(new ArrayList<>());
        if (CollectionUtils.isEmpty(data)) {

            return ProviderCacheMessage.of(new ProviderCache[0]);
        }

        return ProviderCacheMessage.of(data.toArray(new ProviderCache[0]));
    }

    @Override
    public boolean delete(McpQuery query) {
        DeleteResult result = this.mongoCollection.deleteMany(this.convert2Filter(query));

        return result.getDeletedCount() > 0;
    }

    @Override
    public McpProviderInfo info() {
        ProviderCache maxUpdatedDtm =
                this.mongoCollection
                        .find()
                        .sort(Sorts.descending(super.getPropertyFieldName(McpCriteria.Property.UPDATED_DTM)))
                        .limit(1)
                        .first();
        McpProviderInfo result = new McpProviderInfo(super.getIdentifier());

        if (Objects.nonNull(maxUpdatedDtm)) {
            result.setMaxUpdatedDtm(maxUpdatedDtm.getUpdatedDtm());
            result.setCount(this.mongoCollection.countDocuments());
        }

        return result;
    }

    @Override
    protected void destroy() {
        this.mongoClient.close();
    }

    private int convert2Limit(McpQuery query) {

        return Objects.nonNull(query.getLimit()) ? query.getLimit().intValue() : McpConstants.DEFAULT_CACHE_OP_BATCH_LIMIT;
    }

    private Bson convert2Filter(McpQuery query) {
        if (MapUtils.isNotEmpty(query.getCriteriaMap())) {
            List<Bson> filters = new ArrayList<>();
            query.getCriteriaMap().entrySet()
                    .stream()
                    .filter(es -> Objects.nonNull(super.getPropertyFieldName(es.getKey())))
                    .forEach(es -> {
                        String s = es.getValue().toString();
                        if (s.contains("*")) {
                            filters.add(Filters.regex(super.getPropertyFieldName(es.getKey()), s.replace("*", ".*")));
                        } else {
                            filters.add(Filters.eq(super.getPropertyFieldName(es.getKey()), s));
                        }
                    });

            return Filters.and(filters);
        }

        return new BsonDocument();
    }

    private Bson convert2Sort(McpQuery query) {
        if (Objects.nonNull(query.getSortBy())
                && Objects.nonNull(query.getSortDirection())) {
            String name = super.getPropertyFieldName(query.getSortBy());

            return switch (query.getSortDirection()) {
                case ASC -> Sorts.ascending(name);
                case DESC -> Sorts.descending(name);
            };
        }

        return new BsonDocument();
    }

}
