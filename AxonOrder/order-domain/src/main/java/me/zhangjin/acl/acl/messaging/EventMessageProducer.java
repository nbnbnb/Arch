package me.zhangjin.acl.acl.messaging;

import me.zhangjin.acl.domain.entity.event.AbstractEvent;

public interface EventMessageProducer {
    void send(AbstractEvent event);
}
