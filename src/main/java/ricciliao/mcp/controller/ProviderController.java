package ricciliao.mcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.mcp.service.McpProviderPipeline;
import ricciliao.mcp.utils.McpPojoUtils;
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
    private McpProviderPipeline providerPipeline;

    @Autowired
    public void setProviderService(McpProviderInfoService providerService) {
        this.providerService = providerService;
    }

    @Autowired
    public void setProviderPipeline(McpProviderPipeline providerPipeline) {
        this.providerPipeline = providerPipeline;
    }

    @Operation(description = "Create providers")
    @PostMapping("/upsert")
    public Response<PayloadData> upsert(@RequestBody SimplePayloadData.Collection<McpProviderInfoDto> providerInfoList) throws AbstractException {

        return ResponseUtils.success(SimplePayloadData.of(providerPipeline.batch(providerInfoList.data())));
    }

    @Operation(description = "List provider(s)")
    @GetMapping("/list")
    public Response<PayloadData> list() {

        return ResponseUtils.success(
                SimplePayloadData.of(
                        providerService
                                .fullyList()
                                .stream()
                                .map(bo -> McpPojoUtils.convert2Dto(bo.getInfo()))
                                .toList()
                )
        );
    }

}
