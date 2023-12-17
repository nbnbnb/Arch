package me.zhangjin.acl.messing;

import com.alibaba.fastjson.JSON;
import io.micrometer.common.util.StringUtils;
import me.zhangjin.acl.acl.logger.OrderLogger;
import me.zhangjin.acl.acl.messaging.EventMessageConsumer;
import me.zhangjin.acl.domain.entity.event.AbstractEvent;
import me.zhangjin.acl.domain.entity.event.DomainEvent;
import me.zhangjin.acl.handler.ProcessManagerRouter;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventMessageConsumerImpl implements EventMessageConsumer {

    private static Map<String, Class<? extends DomainEvent>> typeMaps = new HashMap<>(16);

    static {
        Reflections reflections = new Reflections();
        Set<Class<? extends DomainEvent>> types = reflections.getSubTypesOf(DomainEvent.class);
        for (Class<? extends DomainEvent> type : types) {
            typeMaps.put(type.getSimpleName(), type);
        }
    }

    @Autowired
    private OrderLogger orderLogger;

    @Autowired
    private ProcessManagerRouter managerRouter;

    @Override
    public void on(String content,String eventType) {
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
        Class<? extends DomainEvent> clazz =  typeMaps.get(eventType);
        return JSON.parseObject(content, clazz);
    }

}
