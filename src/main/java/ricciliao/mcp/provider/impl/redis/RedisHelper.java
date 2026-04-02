package ricciliao.mcp.provider.impl.redis;

import ricciliao.x.mcp.McpIdentifier;

public class RedisHelper {

    private RedisHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String keyPrefix(String consumer, String store) {

        return String.format("%s:%s:", consumer, store);
    }

    public static String keyPrefix(McpIdentifier identifier) {

        return RedisHelper.keyPrefix(identifier.getConsumer(), identifier.getStore());
    }

    public static String indexName(String consumer, String store) {

        return String.format("%s_%s_%s", consumer, store, "index");
    }

    public static String indexName(McpIdentifier identifier) {

        return RedisHelper.indexName(identifier.getConsumer(), identifier.getStore());
    }

    public static String userName(String consumer, String store) {

        return String.format("%s@%s", consumer, store);
    }

}
