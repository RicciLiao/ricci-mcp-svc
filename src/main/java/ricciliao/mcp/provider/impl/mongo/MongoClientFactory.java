package ricciliao.mcp.provider.impl.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.Nonnull;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.PropertiesMongoConnectionDetails;
import ricciliao.mcp.pojo.ProviderCache;
import ricciliao.mcp.properties.McpProviderProperties;
import ricciliao.mcp.properties.MongoProviderProperties;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;
import ricciliao.x.log.api.XLogger;
import ricciliao.x.log.api.XLoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Objects;

public class MongoClientFactory implements AbstractMcpProviderFactory.ClientFactory {

    private static final XLogger logger = XLoggerFactory.getLogger(MongoClientFactory.class);

    private final SSLContext sslContext;

    public MongoClientFactory(MongoProviderProperties.Jks properties) {
        SSLContext temp = null;
        if (Objects.nonNull(properties)) {
            try (InputStream keystoreStream = new FileInputStream(properties.getPath())) {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(keystoreStream, properties.getPassword());
                trustManagerFactory.init(keystore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, trustManagers, null);
                temp = sc;
            } catch (Exception e) {
                logger.warn("Failed to connect mongo by SSL!", e);
            }
        }
        this.sslContext = temp;
    }

    @Nonnull
    @Override
    public MongoClient create(@Nonnull McpProviderProperties providerProperties) {
        MongoProperties mongoProperties = ((MongoProviderProperties) providerProperties).createSpringProperties();
        MongoClientSettings settings =
                MongoClientSettings
                        .builder()
                        .applyConnectionString(new PropertiesMongoConnectionDetails(mongoProperties).getConnectionString())
                        .credential(
                                MongoCredential
                                        .createCredential(
                                                mongoProperties.getUsername(),
                                                mongoProperties.getAuthenticationDatabase(),
                                                mongoProperties.getPassword()
                                        )
                        )
                        .applyToSslSettings(block -> {
                            if (Objects.nonNull(sslContext)) {
                                block.enabled(true).context(this.sslContext);
                            }
                        })
                        .retryReads(true)
                        .retryWrites(true)
                        .codecRegistry(CodecRegistries.fromRegistries(
                                MongoClientSettings.getDefaultCodecRegistry(),
                                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).register(ProviderCache.class).build())
                        ))
                        .build();

        return MongoClients.create(settings);
    }
}
