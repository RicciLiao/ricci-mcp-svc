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
import ricciliao.mcp.provider.AbstractMcpProvider;
import ricciliao.mcp.provider.AbstractMcpProviderLifecycle;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.x.mcp.query.McpCriteria;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MongoProviderLifecycle extends AbstractMcpProviderLifecycle {

    private final MongoClient authMongoClient;
    public final String authenticationDatabase;

    public MongoProviderLifecycle(@Nonnull McpProviderRegistry registry,
                                  @Nonnull MongoClient authMongoClient,
                                  @Nonnull String authenticationDatabase) {
        super(registry);
        this.authMongoClient = authMongoClient;
        this.authenticationDatabase = authenticationDatabase;
    }

    @Override
    protected void preCreation(@Nonnull McpProviderInfoPo po) {
        Document document =
                authMongoClient
                        .getDatabase(authenticationDatabase)
                        .runCommand(
                                BsonDocument.parse(
                                        String.format(
                                                "{usersInfo: \"%s\", filter: {roles: [{role: \"readWrite\", db: \"%s\"}]}}",
                                                po.getConsumer(), po.getConsumer()
                                        )
                                )
                        );
        if (document.getList("users", Document.class).isEmpty()) {
            authMongoClient
                    .getDatabase(authenticationDatabase)
                    .runCommand(
                            BsonDocument.parse(String.format(
                                    "{createUser: \"%s\", pwd: \"%s\", roles: [{role: \"readWrite\", db: \"%s\"}]}",
                                    po.getConsumer(), po.getPasskey().getPassKey(), po.getConsumer()
                            ))
                    );
        }
    }

    @Override
    protected void postCreation(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo po) {
        String ttlIndexName = provider.getIdentifier() + "_ttl_index";
        MongoCollection<ProviderCache> mongoCollection =
                authMongoClient
                        .getDatabase(provider.getIdentifier().getConsumer())
                        .getCollection(provider.getIdentifier().getStore(), ProviderCache.class);
        if (
                mongoCollection
                        .listIndexes()
                        .into(new ArrayList<>())
                        .stream()
                        .filter(index -> ttlIndexName.equalsIgnoreCase(index.getString("name")))
                        .findAny()
                        .isEmpty()
                        && !Boolean.TRUE.equals(po.getIsStatic())
        ) {
            mongoCollection.createIndex(
                    Indexes.ascending(provider.getPropertyFieldName(McpCriteria.Property.TTL)),
                    new IndexOptions()
                            .name(ttlIndexName)
                            .expireAfter(provider.getTtlSeconds().getSeconds(), TimeUnit.SECONDS)
                            .background(true)
            );
        }
    }

    @Override
    protected void preDestruction(@Nonnull AbstractMcpProvider provider, @Nonnull McpProviderInfoPo po) {
        //do nothing
    }

    @Override
    protected void postDestruction(@Nonnull McpProviderInfoPo po) {
        //do nothing
    }
}
