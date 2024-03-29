package me.zhangjin.application.listener;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.application.process.ProcessManagerRouter;
import me.zhangjin.domain.event.common.DomainEvent;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 监听 DomainEvent
 * <p>
 * DomainEvent 表示领域事件，发送这个事件的时候，表示此事件已经发生了
 * <p>
 * Process 服务会监听此事件，基于此类事件循环触发执行业务逻辑（状态机）
 * <p>
 * 注意：只能在 Process 服务中发送 DomainEvent（隐式的，repository.save 会自动触发）
 */
@Component
public class DomainEventMessageListener {

    private static Map<String, Class<? extends DomainEvent>> typeMaps = new HashMap<>(16);

    static {
        // 注意，此处要设置 prefix，路径要对
        Reflections reflections = new Reflections("me.zhangjin.domain.event");
        Set<Class<? extends DomainEvent>> types = reflections.getSubTypesOf(DomainEvent.class);
        for (Class<? extends DomainEvent> type : types) {
            typeMaps.put(type.getTypeName(), type);
        }
    }

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private ProcessManagerRouter managerRouter;

    public void on(String content, String eventType) {
        try {
            DomainEvent domainEvent = resolve(eventType, content);
            if (domainEvent != null) {
                managerRouter.dispatcher(domainEvent);
            } else {
                orderLogger.error("domainevent is empty: %s %s", content, eventType);
            }
        } catch (Exception ex) {
            orderLogger.error("domainevent  resolve error", ex);
            throw ex;
        } finally {
            orderLogger.info("consumer domainevent: %s %s", content, eventType);
        }
    }

    private static DomainEvent resolve(String eventType, String content) {
        Class<? extends DomainEvent> clazz = typeMaps.get(eventType);
        if (clazz == null) {
            throw new RuntimeException("resolve domainevent is null");
        }
        return JSON.parseObject(content, clazz);
    }

}
