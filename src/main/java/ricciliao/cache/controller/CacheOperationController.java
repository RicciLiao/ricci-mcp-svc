package ricciliao.cache.controller;

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
import ricciliao.cache.pojo.ProviderOperation;
import ricciliao.cache.service.CacheService;
import ricciliao.x.cache.annotation.ConsumerId;
import ricciliao.x.cache.pojo.ConsumerIdentifier;
import ricciliao.x.cache.query.CacheBatchQuery;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;

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
    public Response<PayloadData> create(@ConsumerId ConsumerIdentifier identifier,
                                        @RequestBody ProviderOperation.Single single) {

        return ResponseUtils.success(SimpleData.of(cacheService.create(identifier, single)));
    }

    @Operation(description = "Update a existed record for the consumer(with identifier).")
    @PutMapping("")
    public Response<PayloadData> update(@ConsumerId ConsumerIdentifier identifier,
                                        @RequestBody ProviderOperation.Single single) {

        return ResponseUtils.success(SimpleData.of(cacheService.update(identifier, single)));
    }

    @Operation(description = "Delete a existed record for the consumer(with identifier).")
    @DeleteMapping("/{id}")
    public Response<PayloadData> delete(@ConsumerId ConsumerIdentifier identifier,
                                        @PathVariable String id) {

        return ResponseUtils.success(SimpleData.of(cacheService.delete(identifier, id)));
    }

    @Operation(description = "Retrieve a existed record for the consumer(with identifier).")
    @GetMapping("/{id}")
    public ProviderOperation.Single get(@ConsumerId ConsumerIdentifier identifier,
                                        @PathVariable(name = "id") String id) {

        return cacheService.get(identifier, id);
    }

    @Operation(description = "Batch create new records for the consumer(with identifier).")
    @PostMapping("/batch")
    public Response<PayloadData> create(@ConsumerId ConsumerIdentifier identifier,
                                        @RequestBody ProviderOperation.Batch batch) {

        return ResponseUtils.success(SimpleData.of(cacheService.create(identifier, batch)));
    }

    @Operation(description = "Batch delete existed records for the consumer(with identifier).")
    @DeleteMapping("/batch")
    public Response<PayloadData> delete(@ConsumerId ConsumerIdentifier identifier,
                                        @RequestBody CacheBatchQuery query) {

        return ResponseUtils.success(SimpleData.of(cacheService.delete(identifier, query)));
    }

    @Operation(description = "Retrieve list of existed record for the consumer(with identifier).")
    @PostMapping("/list")
    public ProviderOperation.Batch list(@ConsumerId ConsumerIdentifier identifier,
                                        @RequestBody CacheBatchQuery query) {

        return cacheService.list(identifier, query);
    }

}
