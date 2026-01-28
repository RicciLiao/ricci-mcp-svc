package ricciliao.mcp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.mcp.service.CacheService;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.annotation.McpIdentifierHeader;


@Tag(name = "Cache Extra Operation Controller")
@RestController
@RequestMapping("/operation/extra")
public class CacheExtraOperationController {

    private CacheService cacheService;

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Operation(description = "Retrieve provider information for the consumer(with identifier).")
    @GetMapping("/info")
    public Response<PayloadData> info(@McpIdentifierHeader McpIdentifier identifier) {

        return ResponseUtils.success(cacheService.info(identifier));
    }

}
