package me.zhangjin.acl.acl.messaging;

import me.zhangjin.acl.domain.entity.event.AbstractEvent;

public interface EventMessageConsumer {
    void on(String content,String eventType);
}
