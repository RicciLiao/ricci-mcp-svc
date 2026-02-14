package ricciliao.mcp.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ricciliao.mcp.component.McpCacheBatchConverter;
import ricciliao.mcp.component.McpCacheSingleConverter;
import ricciliao.mcp.component.McpIdentifierResolver;
import ricciliao.mcp.component.McpProviderPipeline;
import ricciliao.mcp.provider.AbstractMcpProviderFactory;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.provider.McpProviderRegistry;
import ricciliao.mcp.service.McpProviderInfoService;

import java.util.List;

@Configuration
@ImportAutoConfiguration({
        MongoProviderAutoConfiguration.class,
        RedisProviderAutoConfiguration.class,
})
/*@ComponentScan(
        excludeFilters =
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {CommonAutoConfiguration.CommonWebMvcConfiguration.class}
        ),
        basePackages = "ricciliao.x.starter.common"
)*/
public class McpBeanConfiguration implements WebMvcConfigurer {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new McpCacheSingleConverter(objectMapper, mcpProviderRegistry()));
        converters.add(1, new McpCacheBatchConverter(objectMapper, mcpProviderRegistry()));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new McpIdentifierResolver());
    }

    @Bean
    public McpProviderRegistry mcpProviderRegistry() {

        return new McpProviderRegistry();
    }

    @Bean
    public McpProviderFactoryContext mcpProviderFactoryContext(ObjectProvider<AbstractMcpProviderFactory> provider) {

        return new McpProviderFactoryContext(provider.stream().toList());
    }

    @Bean
    public McpProviderPipeline mcpProviderPipeline(@Autowired McpProviderInfoService service,
                                                   @Autowired McpProviderFactoryContext context) {

        return new McpProviderPipeline(service, context);
    }

}
