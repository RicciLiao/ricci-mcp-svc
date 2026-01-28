package ricciliao.mcp.provider;


import jakarta.annotation.Nonnull;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.BeanCreationException;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.x.mcp.McpCache;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.query.McpCriteria;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractMcpProvider implements McpProviderRepository {

    private final Duration ttlSeconds;
    private final McpIdentifier identifier;
    private final Map<McpCriteria.Property, Field> criteriaProperty2FieldMap = new EnumMap<>(McpCriteria.Property.class);

    protected AbstractMcpProvider(@Nonnull McpProviderInfoPo po) {
        this.identifier = new McpIdentifier(po.getConsumer(), po.getStore());
        this.ttlSeconds = Boolean.TRUE.equals(po.getIsStatic()) ? Duration.ofSeconds(-1L) : Duration.ofSeconds(po.getTtlSeconds());
        Arrays.stream(McpCache.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(McpCriteria.Support.class))
                .forEach(field -> {
                    McpCriteria.Support sortProperty = field.getAnnotation(McpCriteria.Support.class);
                    this.criteriaProperty2FieldMap.put(sortProperty.value(), field);
                });
        if (MapUtils.isEmpty(this.criteriaProperty2FieldMap)) {

            throw new BeanCreationException(
                    String.format(
                            "Initialize McpProvider for consumer: [%s] failed!  Can not identify the @McpCriteria.Support fields",
                            this.identifier
                    )
            );
        }
    }

    public final String getPropertyFieldName(McpCriteria.Property property) {

        return Optional
                .ofNullable(this.criteriaProperty2FieldMap.get(property))
                .map(Field::getName)
                .orElse(null);
    }

    public final Field getPropertyField(McpCriteria.Property property) {

        return criteriaProperty2FieldMap.get(property);
    }

    public final McpIdentifier getIdentifier() {

        return identifier;
    }

    public final Duration getTtlSeconds() {

        return ttlSeconds;
    }

    protected abstract void destroy();

}
