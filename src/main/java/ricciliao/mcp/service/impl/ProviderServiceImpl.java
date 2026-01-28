package ricciliao.mcp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.mcp.pojo.po.McpProviderInfoPo;
import ricciliao.mcp.pojo.po.McpProviderPasskeyPo;
import ricciliao.mcp.provider.McpProviderFactoryContext;
import ricciliao.mcp.repository.McpProviderInfoLogPoRepository;
import ricciliao.mcp.repository.McpProviderInfoPoRepository;
import ricciliao.mcp.repository.McpProviderPasskeyLogRepository;
import ricciliao.mcp.repository.McpProviderPasskeyRepository;
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
    private McpProviderInfoPoRepository providerInfoPoRepository;
    private McpProviderInfoLogPoRepository providerInfoLogPoRepository;
    private McpProviderPasskeyRepository mcpProviderPasskeyRepository;
    private McpProviderPasskeyLogRepository mcpProviderPasskeyLogRepository;

    @Autowired
    public void setMcpProviderPasskeyRepository(McpProviderPasskeyRepository mcpProviderPasskeyRepository) {
        this.mcpProviderPasskeyRepository = mcpProviderPasskeyRepository;
    }

    @Autowired
    public void setMcpProviderPasskeyLogRepository(McpProviderPasskeyLogRepository mcpProviderPasskeyLogRepository) {
        this.mcpProviderPasskeyLogRepository = mcpProviderPasskeyLogRepository;
    }

    @Autowired
    public void setProviderInfoLogPoRepository(McpProviderInfoLogPoRepository providerInfoLogPoRepository) {
        this.providerInfoLogPoRepository = providerInfoLogPoRepository;
    }

    @Autowired
    public void setProviderInfoPoRepository(McpProviderInfoPoRepository providerInfoPoRepository) {
        this.providerInfoPoRepository = providerInfoPoRepository;
    }

    @Autowired
    public void setProviderLifecycle(McpProviderFactoryContext providerLifecycle) {
        this.providerLifecycle = providerLifecycle;
    }


    @Transactional(rollbackOn =  Exception.class)
    @Override
    public boolean initialize() throws AbstractException {
        Instant now = Instant.now();
        List<McpProviderInfoPo> poList = providerInfoPoRepository.initialize();
        Map<String, String> consumer2PasskeyMap = new HashMap<>();
        for (McpProviderInfoPo po : poList) {
            if (Objects.isNull(po.getPasskey())) {
                String passkey = consumer2PasskeyMap.containsKey(po.getConsumer()) ?
                        consumer2PasskeyMap.get(po.getConsumer()) : RandomGenerator.nextString(16).generate();
                McpProviderPasskeyPo passkeyPo = new McpProviderPasskeyPo();
                passkeyPo.setProviderInfoId(po.getId());
                passkeyPo.setPassKey(passkey);
                passkeyPo.setCreatedDtm(now);
                passkeyPo.setUpdatedDtm(now);
                passkeyPo = mcpProviderPasskeyRepository.save(passkeyPo);
                mcpProviderPasskeyLogRepository.save(McpPojoUtils.convert2Po(passkeyPo, LoggerAction.insert()).apply(now));
                po.setPasskey(passkeyPo);
                consumer2PasskeyMap.put(po.getConsumer(), passkeyPo.getPassKey());
            }
            providerLifecycle.create(po);
        }

        return true;
    }
}
