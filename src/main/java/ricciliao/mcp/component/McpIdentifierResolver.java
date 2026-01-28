package ricciliao.mcp.component;

import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ricciliao.x.mcp.McpConstants;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.annotation.McpIdentifierHeader;

public class McpIdentifierResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(McpIdentifierHeader.class)
                && parameter.getParameterType().isAssignableFrom(McpIdentifier.class);
    }

    @Override
    public McpIdentifier resolveArgument(@Nonnull MethodParameter parameter,
                                         ModelAndViewContainer mavContainer,
                                         NativeWebRequest webRequest,
                                         WebDataBinderFactory binderFactory) {

        return new McpIdentifier(
                webRequest.getHeader(McpConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER),
                webRequest.getHeader(McpConstants.HTTP_HEADER_FOR_CACHE_STORE)
        );
    }

}
