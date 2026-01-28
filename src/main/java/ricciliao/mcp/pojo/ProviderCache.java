package ricciliao.mcp.pojo;


import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import ricciliao.x.mcp.McpCache;

import java.io.Serial;

@BsonDiscriminator
public class ProviderCache extends McpCache<byte[]> {
    @Serial
    private static final long serialVersionUID = -1634685567608547710L;

}
