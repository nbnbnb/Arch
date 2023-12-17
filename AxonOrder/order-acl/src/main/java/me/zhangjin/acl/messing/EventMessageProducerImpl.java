package me.zhangjin.acl.messing;

import com.alibaba.fastjson.JSON;
import me.zhangjin.acl.acl.logger.OrderLogger;
import me.zhangjin.acl.acl.messaging.EventMessageProducer;
import me.zhangjin.acl.domain.entity.event.AbstractEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMessageProducerImpl implements EventMessageProducer {

    @Autowired
    private OrderLogger orderLogger;

    @Override
    public void send(AbstractEvent event) {
        // TODO 发送持久化 MQ
        orderLogger.info("send event message: " + JSON.toJSONString(event));
    }
}
