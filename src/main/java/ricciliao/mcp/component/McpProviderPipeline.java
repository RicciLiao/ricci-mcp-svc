package ricciliao.mcp.component;

import org.springframework.transaction.annotation.Transactional;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.util.function.Function;
import java.util.function.Supplier;

public class McpProviderPipeline {

    private final McpProviderInfoService service;
    private final McpProviderFactoryContext factoryContext;

    public McpProviderPipeline(McpProviderInfoService service,
                               McpProviderFactoryContext factoryContext) {
        this.service = service;
        this.factoryContext = factoryContext;
        Action.setSuppliers(() -> this.service, () -> this.factoryContext);
    }

    public enum Action {
        CREATE(new Function<>() {
            @Transactional(rollbackFor = Exception.class)
            @Override
            public Boolean apply(McpProviderInfoDto dto) {
                McpProviderInfoService service = getServiceSupplier().get();
                Long id = SneakyThrowUtils.get(() -> service.insert(dto));
                McpProviderInfoBo bo = service.pipelinePreStartup(id);
                SneakyThrowUtils.get(() -> getContextSupplier().get().create(bo));

                return true;
            }
        }),
        ;

        final Function<McpProviderInfoDto, Boolean> processor;
        private static Supplier<McpProviderInfoService> serviceSupplier;
        private static Supplier<McpProviderFactoryContext> contextSupplier;

        Action(Function<McpProviderInfoDto, Boolean> processor) {
            this.processor = processor;
        }

        private static void setSuppliers(Supplier<McpProviderInfoService> service,
                                         Supplier<McpProviderFactoryContext> context) {
            serviceSupplier = service;
            contextSupplier = context;
        }

        private static Supplier<McpProviderInfoService> getServiceSupplier() {

            return serviceSupplier;
        }

        private static Supplier<McpProviderFactoryContext> getContextSupplier() {

            return contextSupplier;
        }

    }

}
