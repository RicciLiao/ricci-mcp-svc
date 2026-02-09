package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.common.McpSecondaryCodeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class McpProviderFactoryContext {

    private final Map<McpProviderEnum, AbstractMcpProviderFactory> providerFactoryMap;

    public McpProviderFactoryContext(List<AbstractMcpProviderFactory> providerFactorieList) {
        this.providerFactoryMap = new EnumMap<>(McpProviderEnum.class);
        for (AbstractMcpProviderFactory providerFactory : providerFactorieList) {
            providerFactoryMap.put(providerFactory.whoAmI(), providerFactory);
        }
    }

    protected Optional<AbstractMcpProviderFactory> get(@Nonnull McpProviderInfoBo bo) {

        return providerFactoryMap
                .entrySet()
                .stream()
                .filter(entry ->
                        entry.getKey().getProvider().equalsIgnoreCase(bo.getType().getProvider()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public AbstractMcpProvider create(@Nonnull McpProviderInfoBo bo) throws AbstractException {
        Optional<? extends AbstractMcpProviderFactory> factory = this.get(bo);
        if (factory.isEmpty()) {

            throw new DataException(McpSecondaryCodeEnum.PROVIDER_FACTORY_NOT_EXISTED.format(bo.getInfo().getConsumer(), bo.getInfo().getStore()));
        }

        return factory.get().create(bo);
    }

    public void destroy(@Nonnull McpProviderInfoBo bo) throws AbstractException {
        Optional<AbstractMcpProviderFactory> factory = this.get(bo);
        if (factory.isEmpty()) {

            throw new DataException(McpSecondaryCodeEnum.PROVIDER_FACTORY_NOT_EXISTED.format(bo.getInfo().getConsumer(), bo.getInfo().getStore()));
        }
        factory.get().destroy(bo);
    }

}
