package ricciliao.mcp.service.impl;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.mcp.common.McpSecondaryCodeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.pojo.po.McpProviderStatusPo;
import ricciliao.mcp.repository.McpProviderInfoLogRepository;
import ricciliao.mcp.repository.McpProviderInfoRepository;
import ricciliao.mcp.repository.McpProviderPassInfoLogRepository;
import ricciliao.mcp.repository.McpProviderPassInfoRepository;
import ricciliao.mcp.repository.McpProviderStatusLogRepository;
import ricciliao.mcp.repository.McpProviderStatusRepository;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.mcp.utils.McpPojoUtils;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.ConcurrentException;
import ricciliao.x.component.exception.DuplicateException;
import ricciliao.x.component.persistence.LogAction;
import ricciliao.x.component.persistence.ModifiableAction;
import ricciliao.x.component.random.RandomGenerator;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service("mcpProviderInfoService")
public class McpProviderInfoServiceImpl implements McpProviderInfoService {

    private McpProviderInfoRepository providerInfoRepository;
    private McpProviderInfoLogRepository providerInfoLogRepository;
    private McpProviderPassInfoRepository providerPassInfoRepository;
    private McpProviderPassInfoLogRepository providerPassInfoLogRepository;
    private McpProviderStatusRepository providerStatusRepository;
    private McpProviderStatusLogRepository providerStatusLogRepository;

    @Autowired
    public void setProviderStatusRepository(McpProviderStatusRepository providerStatusRepository) {
        this.providerStatusRepository = providerStatusRepository;
    }

    @Autowired
    public void setProviderStatusLogRepository(McpProviderStatusLogRepository providerStatusLogRepository) {
        this.providerStatusLogRepository = providerStatusLogRepository;
    }

    @Autowired
    public void setProviderPassInfoRepository(McpProviderPassInfoRepository providerPassInfoRepository) {
        this.providerPassInfoRepository = providerPassInfoRepository;
    }

    @Autowired
    public void setProviderPassInfoLogRepository(McpProviderPassInfoLogRepository providerPassInfoLogRepository) {
        this.providerPassInfoLogRepository = providerPassInfoLogRepository;
    }

    @Autowired
    public void setProviderInfoLogRepository(McpProviderInfoLogRepository providerInfoLogRepository) {
        this.providerInfoLogRepository = providerInfoLogRepository;
    }

    @Autowired
    public void setProviderInfoRepository(McpProviderInfoRepository providerInfoRepository) {
        this.providerInfoRepository = providerInfoRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public McpProviderInfoBo pipelinePreStartup(Long id) {
        Instant now = Instant.now();
        McpProviderInfoBo result = providerInfoRepository.fullyGet(id);
        if (Objects.isNull(result.getPassInfo())) {
            McpProviderPassInfoPo passInfoPo = new McpProviderPassInfoPo();
            passInfoPo.setProviderInfoId(result.getInfo().getId());
            passInfoPo.setPassKey(RandomGenerator.nextString(16).generate());
            passInfoPo.setCreatedBy(0L);
            passInfoPo.setCreatedDtm(now);
            passInfoPo.setUpdatedBy(0L);
            passInfoPo.setUpdatedDtm(now);
            passInfoPo = providerPassInfoRepository.saveAndFlush(passInfoPo);
            providerPassInfoLogRepository.save(McpPojoUtils.convert2Po(passInfoPo, LogAction.insert(now)));
            result.setPassInfo(passInfoPo);
        }
        if (Objects.isNull(result.getStatus())) {
            McpProviderStatusPo statusPo = new McpProviderStatusPo();
            statusPo.setProviderInfoId(result.getStatus().getProviderInfoId());
            statusPo.setStatus(Boolean.TRUE);
            statusPo.setCreatedBy(0L);
            statusPo.setCreatedDtm(now);
            statusPo.setUpdatedBy(0L);
            statusPo.setUpdatedDtm(now);
            statusPo = providerStatusRepository.saveAndFlush(statusPo);
            providerStatusLogRepository.save(McpPojoUtils.convert2Po(statusPo, LogAction.insert(now)));
            result.setStatus(statusPo);
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insert(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (providerInfoRepository.existsByConsumerAndStore(dto.getConsumer(), dto.getStore())) {

            throw new DuplicateException(McpSecondaryCodeEnum.CONSUMER_STORE_EXISTED.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto, ModifiableAction.insert(now));
        po = providerInfoRepository.saveAndFlush(po);
        providerInfoLogRepository.save(McpPojoUtils.convert2Po(po, LogAction.insert(now)));

        return po.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long update(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (providerInfoRepository.existsById(dto.getId())) {

            throw new ConcurrentException(McpSecondaryCodeEnum.PROVIDER_NOT_FIND.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto, ModifiableAction.update(now));
        po = providerInfoRepository.saveAndFlush(po);
        providerInfoLogRepository.save(McpPojoUtils.convert2Po(po, LogAction.update(now)));

        return 0L;
    }

    @Override
    public McpProviderInfoDto get(Long id) throws AbstractException {
        McpProviderInfoBo bo = providerInfoRepository.fullyGet(id);
        if (Objects.isNull(bo)) {

            return null;
        }
        McpProviderInfoDto result = McpPojoUtils.convert2Dto(bo.getInfo());
        result.setStatus(McpPojoUtils.convert2Dto(bo.getStatus()));

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long delete(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (providerInfoRepository.existsById(dto.getId())) {

            throw new ConcurrentException(McpSecondaryCodeEnum.PROVIDER_NOT_FIND.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto, ModifiableAction.delete(now));
        providerInfoRepository.delete(po);
        providerInfoLogRepository.save(McpPojoUtils.convert2Po(po, LogAction.delete(now)));
        Optional<McpProviderPassInfoPo> passInfoPoOptional = providerPassInfoRepository.findById(dto.getId());
        if (passInfoPoOptional.isPresent()) {
            McpProviderPassInfoPo passInfoPo = passInfoPoOptional.get();
            passInfoPo.setUpdatedBy(po.getUpdatedBy());
            passInfoPo.setUpdatedDtm(now);
            providerPassInfoRepository.delete(passInfoPo);
            providerPassInfoLogRepository.save(McpPojoUtils.convert2Po(passInfoPo, LogAction.delete(now)));
        }

        return 0L;
    }

}
