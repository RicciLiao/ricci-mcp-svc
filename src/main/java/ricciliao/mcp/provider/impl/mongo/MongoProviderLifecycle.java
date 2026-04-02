package ricciliao.mcp.provider.impl.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import jakarta.annotation.Nonnull;
import org.bson.BsonDocument;
import org.bson.Document;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderLifecycle;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.mcp.query.McpCriteria;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MongoProviderLifecycle extends AbstractMcpProviderLifecycle {

    public final String authenticationDatabase;
    private final MongoClient authMongoClient;

    public MongoProviderLifecycle(@Nonnull McpProviderRegistry registry,
                                  @Nonnull MongoClient authMongoClient,
                                  @Nonnull String authenticationDatabase) {
        super(registry);
        this.authMongoClient = authMongoClient;
        this.authenticationDatabase = authenticationDatabase;
    }

    @Override
    protected void preCreation(@Nonnull McpProviderInfoPo info, @Nonnull McpProviderPassInfoPo passInfo) {
        String userName = MongoHelper.userName(info.getConsumer(), info.getStore());
        Document document =
                authMongoClient
                        .getDatabase(authenticationDatabase)
                        .runCommand(BsonDocument.parse(String.format(
                                "{usersInfo: \"%s\", filter: {roles: [{role: \"readWrite\", db: \"%s\"}]}}",
                                userName, info.getConsumer()
                        )));
        if (document.getList("users", Document.class).isEmpty()) {
            authMongoClient
                    .getDatabase(authenticationDatabase)
                    .runCommand(BsonDocument.parse(String.format(
                            "{createUser: \"%s\", pwd: \"%s\", roles: [{role: \"readWrite\", db: \"%s\"}]}",
                            userName, passInfo.getPassKey(), info.getConsumer())
                    ));
        }
    }

    @Override
    protected void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info) {
        String indexName = MongoHelper.indexName(info.getConsumer(), info.getStore());
        MongoCollection<ProviderCache> mongoCollection =
                authMongoClient
                        .getDatabase(info.getConsumer())
                        .getCollection(info.getStore(), ProviderCache.class);
        if (
                mongoCollection
                        .listIndexes()
                        .into(new ArrayList<>())
                        .stream()
                        .filter(index -> indexName.equalsIgnoreCase(index.getString("name")))
                        .findAny()
                        .isEmpty()
                && !Boolean.TRUE.equals(info.getStatical())
        ) {
            mongoCollection.createIndex(
                    Indexes.ascending(provider.getPropertyFieldName(McpCriteria.Property.TTL)),
                    new IndexOptions()
                            .name(indexName)
                            .expireAfter(provider.getTtlSeconds().getSeconds(), TimeUnit.SECONDS)
                            .background(true)
            );
        }
    }

    @Override
    protected void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo info) {
        authMongoClient.getDatabase(info.getConsumer()).drop();
        authMongoClient
                .getDatabase(authenticationDatabase)
                .runCommand(BsonDocument.parse(String.format(
                        "{dropUser: \"%s\"}",
                        MongoHelper.userName(info.getConsumer(), info.getStore())
                )));
    }

    @Override
    protected void postDestruction(@Nonnull McpProviderInfoPo info) {
        //do nothing
    }
}
