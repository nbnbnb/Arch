package me.zhangjin.application.listener;

import com.alibaba.fastjson.JSON;
import me.zhangjin.application.process.ProcessManagerRouter;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.command.DomainCommand;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


// 接收外部消息
// 例如 支付回调/供应商回调
// 然后将其反序列化为 DomainCommand ，发送到 MessageBus 中执行
@Component
public class CommandMessageListener {

    private static Map<String, Class<? extends DomainCommand>> typeMaps = new HashMap<>(16);

    static {
        Reflections reflections = new Reflections();
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
        return JSON.parseObject(content, clazz);
    }

}
