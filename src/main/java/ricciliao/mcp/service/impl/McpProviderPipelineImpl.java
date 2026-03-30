package ricciliao.mcp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.mcp.service.McpProviderPipeline;
import ricciliao.x.component.exception.AbstractException;

import java.util.List;
import java.util.Objects;

@Service("mcpProviderPipeline")
public class McpProviderPipelineImpl implements McpProviderPipeline {

    private McpProviderInfoService providerInfoService;
    private McpProviderFactoryContext providerFactoryContext;

    @Autowired
    public void setProviderFactoryContext(McpProviderFactoryContext providerFactoryContext) {
        this.providerFactoryContext = providerFactoryContext;
    }

    @Autowired
    public void setProviderInfoService(McpProviderInfoService providerInfoService) {
        this.providerInfoService = providerInfoService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean startup() throws AbstractException {
        List<McpProviderInfoBo> boList =
                providerInfoService
                        .fullyList()
                        .stream()
                        .filter(bo -> bo.getInfo().getActive())
                        .toList();
        for (McpProviderInfoBo bo : boList) {
            providerFactoryContext.create(bo);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(McpProviderInfoDto dto) throws AbstractException {
        Long id = providerInfoService.insert(dto);
        if (Boolean.TRUE.equals(dto.getActive())) {
            McpProviderInfoBo bo = providerInfoService.fullyGet(id);
            providerFactoryContext.create(bo);
        }

        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long update(McpProviderInfoDto dto) throws AbstractException {
        Long id = providerInfoService.update(dto);
        McpProviderInfoBo bo = providerInfoService.fullyGet(id);
        providerFactoryContext.destroy(bo);
        if (Boolean.TRUE.equals(bo.getInfo().getActive())) {
            providerFactoryContext.create(bo);
        }

        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long delete(McpProviderInfoDto dto) throws AbstractException {
        McpProviderInfoBo bo = providerInfoService.fullyGet(dto.getId());
        providerFactoryContext.destroy(bo);

        return providerInfoService.delete(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batch(List<McpProviderInfoDto> dtoList) throws AbstractException {
        for (McpProviderInfoDto dto : dtoList) {
            if (Boolean.TRUE.equals(dto.getDeleted())) {
                this.delete(dto);
            } else if (Objects.nonNull(dto.getId())) {
                this.update(dto);
            } else {
                this.create(dto);
            }
        }

        return false;
    }
}
