package ricciliao.mcp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Transactional(rollbackOn =  Exception.class)
    @Override
    public boolean initialize() throws AbstractException {
        Instant now = Instant.now();
        List<McpProviderInfoPo> poList = providerInfoRepository.initialize();
        Map<String, String> consumer2PasskeyMap = new HashMap<>();
        for (McpProviderInfoPo po : poList) {
            if (Objects.isNull(po.getPassInfo())) {
                String passkey = consumer2PasskeyMap.containsKey(po.getConsumer()) ?
                        consumer2PasskeyMap.get(po.getConsumer()) : RandomGenerator.nextString(16).generate();
                McpProviderPassInfoPo passkeyPo = new McpProviderPassInfoPo();
                passkeyPo.setProviderInfoId(po.getId());
                passkeyPo.setPassKey(passkey);
                passkeyPo.setCreatedDtm(now);
                passkeyPo.setUpdatedDtm(now);
                passkeyPo = providerPassInfoRepository.save(passkeyPo);
                providerPassInfoLogRepository.save(McpPojoUtils.convert2Po(passkeyPo, LoggerAction.insert()).apply(now));
                po.setPassInfo(passkeyPo);
                consumer2PasskeyMap.put(po.getConsumer(), passkeyPo.getPassKey());
            }
            providerLifecycle.create(po);
        }

        return true;
    }
}
