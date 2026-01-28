package ricciliao.mcp.provider;

import jakarta.annotation.Nonnull;
import ricciliao.mcp.common.McpProviderEnum;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.x.component.exception.AbstractException;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class McpProviderFactoryContext {

    private final Map<McpProviderEnum, AbstractMcpProviderFactory> providerFactoryMap;

    public McpProviderFactoryContext(List<AbstractMcpProviderFactory> providerFactorieList) {
        this.providerFactoryMap = new EnumMap<>(McpProviderEnum.class);
        for (AbstractMcpProviderFactory providerFactory : providerFactorieList) {
            providerFactoryMap.put(providerFactory.whoAmI(), providerFactory);
        }
    }

    public Optional<AbstractMcpProviderFactory> get(@Nonnull McpProviderInfoPo po) {

        return providerFactoryMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getIdentity().equals(po.getProvider()))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public AbstractMcpProvider create(@Nonnull McpProviderInfoPo po) throws AbstractException {
        Optional<? extends AbstractMcpProviderFactory> factory = this.get(po);
        if (factory.isEmpty()) {

            throw new NoSuchElementException(String.format("McpProviderFactory[consumer: %s, store: %s] does not exist", po.getConsumer(), po.getStore()));
        }

        return factory.get().delegateCreate(po);
    }

    public void destroy(@Nonnull McpProviderInfoPo po) {
        Optional<AbstractMcpProviderFactory> factory = this.get(po);
        if (factory.isEmpty()) {

            throw new NoSuchElementException(String.format("McpProviderFactory[consumer: %s, store: %s] does not exist", po.getConsumer(), po.getStore()));
        }
        factory.get().delegateDestroy(po);
    }

}
