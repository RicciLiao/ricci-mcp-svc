package ricciliao.mcp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.mcp.common.McpSecondaryCodeEnum;
import ricciliao.mcp.pojo.bo.McpProviderInfoBo;
import ricciliao.mcp.pojo.dto.McpProviderInfoDto;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPassInfoPo;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.repository.McpProviderInfoLogRepository;
import ricciliao.mcp.repository.McpProviderInfoRepository;
import ricciliao.mcp.repository.McpProviderPassInfoLogRepository;
import ricciliao.mcp.repository.McpProviderPassInfoRepository;
import ricciliao.mcp.service.ProviderService;
import ricciliao.mcp.utils.McpPojoUtils;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DuplicateException;
import ricciliao.x.component.persistence.LoggerAction;
import ricciliao.x.component.random.RandomGenerator;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    private McpProviderFactoryContext providerLifecycle;
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

    @Autowired
    public void setProviderLifecycle(McpProviderFactoryContext providerLifecycle) {
        this.providerLifecycle = providerLifecycle;
    }

    @Transactional(rollbackFor = JpaSystemException.class, noRollbackFor = Exception.class)
    @Override
    public boolean initialize() throws AbstractException {
        Instant now = Instant.now();
        List<McpProviderInfoBo> boList = providerInfoRepository.initialize();
        Map<String, String> consumer2PasskeyMap = new HashMap<>();
        for (McpProviderInfoBo bo : boList) {
            if (Objects.isNull(bo.getPassInfo())) {
                String passkey = consumer2PasskeyMap.containsKey(bo.getInfo().getConsumer()) ?
                        consumer2PasskeyMap.get(bo.getInfo().getConsumer()) : RandomGenerator.nextString(16).generate();
                McpProviderPassInfoPo passkeyPo = new McpProviderPassInfoPo();
                passkeyPo.setProviderInfoId(bo.getInfo().getId());
                passkeyPo.setPassKey(passkey);
                passkeyPo.setCreatedBy(0L);
                passkeyPo.setCreatedDtm(now);
                passkeyPo.setUpdatedBy(0L);
                passkeyPo.setUpdatedDtm(now);
                passkeyPo = providerPassInfoRepository.saveAndFlush(passkeyPo);
                providerPassInfoLogRepository.save(McpPojoUtils.convert2Po(passkeyPo, LoggerAction.insert()).apply(now));
                bo.setPassInfo(passkeyPo);
                consumer2PasskeyMap.put(bo.getInfo().getConsumer(), passkeyPo.getPassKey());
            }
            providerLifecycle.create(bo);
        }

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(McpProviderInfoDto dto) throws AbstractException {
        if (providerInfoRepository.existsByConsumerAndStore(dto.getConsumer(), dto.getStore())) {

            throw new DuplicateException(McpSecondaryCodeEnum.CONSUMER_STORE_EXISTED.format(dto.getConsumer(), dto.getStore()));
        }
        Instant now = Instant.now();
        McpProviderInfoPo po = McpPojoUtils.convert2Po(dto);
        po = providerInfoRepository.saveAndFlush(po);
        providerInfoLogRepository.save(McpPojoUtils.covert2Po(po, LoggerAction.insert()).apply(now));

        return po.getId();
    }
}
