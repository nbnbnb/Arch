package me.zhangjin.domain.acl.messaging;

import java.time.LocalDateTime;
import java.util.Map;

public interface MessageProducer {
    void send(String topic, Map<String, Object> data);

    void send(String topic, Map<String, Object> data, LocalDateTime delayTime);
}
