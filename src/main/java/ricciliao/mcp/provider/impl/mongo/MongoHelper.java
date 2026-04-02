package ricciliao.mcp.provider.impl.mongo;

public class MongoHelper {

    private MongoHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String indexName(String consumer, String store) {

        return String.format("%s_%s_%s", consumer, store, "index");
    }

    public static String userName(String consumer, String store) {

        return String.format("%s@%s", consumer, store);
    }

}
