package ricciliao.mcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;

@Tag(name = "Provider Controller")
@RestController
@RequestMapping("/provider")
public class ProviderController {

    private McpProviderInfoService providerService;

    @Autowired
    public void setProviderService(McpProviderInfoService providerService) {
        this.providerService = providerService;
    }

    @Operation(description = "Create provider")
    @PostMapping("")
    public Response<PayloadData> create(@RequestBody McpProviderInfoDto providerInfo) throws AbstractException {
        Long id = providerService.insert(providerInfo);

        return ResponseUtils.success(SimplePayloadData.of(id.toString()));
    }

}
