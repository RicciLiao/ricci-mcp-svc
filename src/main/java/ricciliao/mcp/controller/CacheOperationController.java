package ricciliao.mcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.mcp.pojo.AbstractProviderCacheMessage;
import ricciliao.mcp.service.CacheService;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;
import ricciliao.x.mcp.McpIdentifier;
import ricciliao.x.mcp.annotation.McpIdentifierHeader;
import ricciliao.x.mcp.query.McpQuery;

@Tag(name = "Cache Operation Controller")
@RestController
@RequestMapping("/operation")
public class CacheOperationController {

    private CacheService cacheService;

    @Autowired
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Operation(description = "Create a new record for the consumer(with identifier).")
    @PostMapping("")
    public Response<PayloadData> create(@McpIdentifierHeader McpIdentifier identifier,
                                        @RequestBody AbstractProviderCacheMessage.Single single) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.create(identifier, single)));
    }

    @Operation(description = "Update a existed record for the consumer(with identifier).")
    @PutMapping("")
    public Response<PayloadData> update(@McpIdentifierHeader McpIdentifier identifier,
                                        @RequestBody AbstractProviderCacheMessage.Single single) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.update(identifier, single)));
    }

    @Operation(description = "Delete a existed record for the consumer(with identifier).")
    @DeleteMapping("/{id}")
    public Response<PayloadData> delete(@McpIdentifierHeader McpIdentifier identifier,
                                        @PathVariable(name = "id") String id) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.delete(identifier, id)));
    }

    @Operation(description = "Retrieve a existed record for the consumer(with identifier).")
    @GetMapping("/{id}")
    public AbstractProviderCacheMessage.Single get(@McpIdentifierHeader McpIdentifier identifier,
                                                   @PathVariable(name = "id") String id) {

        return cacheService.get(identifier, id);
    }

    @Operation(description = "Batch create new records for the consumer(with identifier).")
    @PostMapping("/batch")
    public Response<PayloadData> create(@McpIdentifierHeader McpIdentifier identifier,
                                        @RequestBody AbstractProviderCacheMessage.Batch batch) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.create(identifier, batch)));
    }


    @Operation(description = "Batch Update existed records for the consumer(with identifier).")
    @PutMapping("/batch")
    public Response<PayloadData> update(@McpIdentifierHeader McpIdentifier identifier,
                                        @RequestBody AbstractProviderCacheMessage.Batch batch) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.update(identifier, batch)));
    }

    @Operation(description = "Batch delete existed records for the consumer(with identifier).")
    @DeleteMapping("/batch")
    public Response<PayloadData> delete(@McpIdentifierHeader McpIdentifier identifier,
                                        @RequestBody McpQuery query) {

        return ResponseUtils.success(SimplePayloadData.of(cacheService.delete(identifier, query)));
    }

    @Operation(description = "Retrieve list of existed record for the consumer(with identifier).")
    @PostMapping("/list")
    public AbstractProviderCacheMessage.Batch list(@McpIdentifierHeader McpIdentifier identifier,
                                                   @RequestBody McpQuery query) {

        return cacheService.list(identifier, query);
    }

}
