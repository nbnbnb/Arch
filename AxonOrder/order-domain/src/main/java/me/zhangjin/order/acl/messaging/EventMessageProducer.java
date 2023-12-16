package me.zhangjin.order.acl.messaging;

import me.zhangjin.order.domain.entity.event.AbstractEvent;

public interface EventMessageProducer {
    void send(AbstractEvent event);
}
