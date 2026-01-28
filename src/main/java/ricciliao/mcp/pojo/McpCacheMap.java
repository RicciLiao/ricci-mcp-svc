package ricciliao.mcp.pojo;

import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.mcp.McpCache;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class McpCacheMap extends McpCache<LinkedHashMap<String, Serializable>> implements PayloadData {
    @Serial
    private static final long serialVersionUID = 2714246958676385539L;

}
