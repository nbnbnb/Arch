package me.zhangjin.application.listener;

import com.alibaba.fastjson.JSON;
import me.zhangjin.application.process.ProcessManagerRouter;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.command.common.DomainCommand;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 监听 DomainCommand
 * <p>
 * 接收外部事件，触发系统状态的改变：例如支付回调事件/供应商确认事件
 * <p>
 * 外部消息需要触发系统状态变更，必须发送 DomainCommand（与 SOA 接口请求一致）
 * <p>
 * SOA 是将 RequestType 转换为 DomainCommand，而 DomainCommand MQ 是直接执行反序列化得到 DomainCommand
 */

@Component
public class DomainCommandMessageListener {

    private static Map<String, Class<? extends DomainCommand>> typeMaps = new HashMap<>(16);

    static {
        // 注意，此处要设置 prefix，路径要对
        Reflections reflections = new Reflections("me.zhangjin.domain.command");
        Set<Class<? extends DomainCommand>> types = reflections.getSubTypesOf(DomainCommand.class);
        for (Class<? extends DomainCommand> type : types) {
            typeMaps.put(type.getTypeName(), type);
        }
    }

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private ProcessManagerRouter managerRouter;

    public void on(String content, String eventType) {
        try {
            DomainCommand domainCommand = resolve(eventType, content);
            if (domainCommand != null) {
                // 发送到 Message Bus 中执行
                managerRouter.dispatcher(domainCommand);
            } else {
                orderLogger.error("domaincommand is empty: %s %s", content, eventType);
            }
        } catch (Exception ex) {
            orderLogger.error("domaincommand  resolve error", ex);
            throw ex;
        } finally {
            orderLogger.info("consumer domaincommand: %s %s", content, eventType);
        }
    }

    private static DomainCommand resolve(String eventType, String content) {
        Class<? extends DomainCommand> clazz = typeMaps.get(eventType);
        if (clazz == null) {
            throw new RuntimeException("resolve domaincommand is null");
        }
        return JSON.parseObject(content, clazz);
    }

}
