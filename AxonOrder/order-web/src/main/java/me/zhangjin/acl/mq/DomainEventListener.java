package me.zhangjin.acl.mq;


import me.zhangjin.acl.acl.messaging.EventMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 接收外部的消息

@Component
public class DomainEventListener {

    @Autowired
    private EventMessageConsumer eventMessageConsumer;

    public void on(String content, String eventType) {
        eventMessageConsumer.on(content, eventType);
    }
}
