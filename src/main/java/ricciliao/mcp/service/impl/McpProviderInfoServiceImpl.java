package ricciliao.mcp.service.impl;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.mcp.common.McpSecondaryCodeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.repository.McpProviderInfoLogRepository;
import ricciliao.mcp.repository.McpProviderInfoRepository;
import ricciliao.mcp.repository.McpProviderPassInfoLogRepository;
import ricciliao.mcp.repository.McpProviderPassInfoRepository;
import ricciliao.mcp.service.McpProviderInfoService;
import ricciliao.mcp.utils.McpPojoUtils;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.component.persistence.LogAction;
import ricciliao.x.component.persistence.ModifiableAction;
import ricciliao.x.component.random.RandomGenerator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("mcpProviderInfoService")
public class McpProviderInfoServiceImpl implements McpProviderInfoService {

    private McpProviderInfoRepository providerInfoRepository;
    private McpProviderInfoLogRepository providerInfoLogRepository;
    private McpProviderPassInfoRepository providerPassInfoRepository;
    private McpProviderPassInfoLogRepository providerPassInfoLogRepository;

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

    @Override
    public McpProviderInfoBo fullyGet(Long id) {

        return providerInfoRepository.fullyGet(id);
    }

    @Override
    public List<McpProviderInfoBo> fullyList() {

        return providerInfoRepository.fullyList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insert(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (providerInfoRepository.existsByConsumerAndStore(dto.getConsumer(), dto.getStore())) {

            throw new DataException(McpSecondaryCodeEnum.CONSUMER_STORE_EXISTED.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto, ModifiableAction.insert(now));
        po = providerInfoRepository.saveAndFlush(po);
        providerInfoLogRepository.save(McpPojoUtils.convert2Po(po, LogAction.insert(now)));
        //Pass Info
        McpProviderPassInfoPo passInfoPo = new McpProviderPassInfoPo();
        passInfoPo.setProviderInfoId(po.getId());
        passInfoPo.setPassKey(RandomGenerator.nextString(16).generate());
        passInfoPo.setCreatedBy(po.getCreatedBy());
        passInfoPo.setCreatedDtm(now);
        passInfoPo.setUpdatedBy(po.getUpdatedBy());
        passInfoPo.setUpdatedDtm(now);
        passInfoPo = providerPassInfoRepository.saveAndFlush(passInfoPo);
        providerPassInfoLogRepository.save(McpPojoUtils.convert2Po(passInfoPo, LogAction.insert(now)));

        return po.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long update(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (!providerInfoRepository.existsById(dto.getId())) {

            throw new DataException(McpSecondaryCodeEnum.PROVIDER_NOT_FIND.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto, ModifiableAction.update(now));
        po = providerInfoRepository.saveAndFlush(po);
        providerInfoLogRepository.save(McpPojoUtils.convert2Po(po, LogAction.update(now)));

        return po.getId();
    }

    @Override
    public McpProviderInfoDto get(Long id) throws AbstractException {
        McpProviderInfoBo bo = providerInfoRepository.fullyGet(id);
        if (Objects.isNull(bo)) {

            return null;
        }

        return McpPojoUtils.convert2Dto(bo.getInfo());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long delete(@Nonnull McpProviderInfoDto dto) throws AbstractException {
        if (!providerInfoRepository.existsById(dto.getId())) {

            throw new DataException(McpSecondaryCodeEnum.PROVIDER_NOT_FIND.format(dto.getConsumer(), dto.getStore()));
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

        return po.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upsert(@NonNull List<McpProviderInfoDto> dtoList) throws AbstractException {
        for (McpProviderInfoDto dto : dtoList) {
            if (Boolean.TRUE.equals(dto.getDeleted())) {
                this.delete(dto);
            } else if (Objects.isNull(dto.getId())) {
                this.insert(dto);
            } else {
                this.update(dto);
            }
        }

        return true;
    }

}
