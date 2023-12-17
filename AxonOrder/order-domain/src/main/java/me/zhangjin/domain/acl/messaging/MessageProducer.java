package me.zhangjin.domain.acl.messaging;

import java.util.Map;

public interface MessageProducer {
    void send(String topic, Map<String, Object> data);
}
