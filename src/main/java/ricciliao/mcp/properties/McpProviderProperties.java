package ricciliao.mcp.properties;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public interface McpProviderProperties extends Serializable {

    Object createSpringProperties();

    default McpProviderProperties copy() {

        return SerializationUtils.clone(this);
    }

}
