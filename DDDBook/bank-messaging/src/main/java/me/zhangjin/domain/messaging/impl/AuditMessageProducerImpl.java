package me.zhangjin.domain.messaging.impl;

import me.zhangjin.domain.message.AuditMessage;
import me.zhangjin.domain.messaging.AuditMessageProducer;
import me.zhangjin.domain.types.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuditMessageProducerImpl implements AuditMessageProducer {

    private static final String TOPIC_AUDIT_LOG = "TOPIC_AUDIT_LOG";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public SendResult send(AuditMessage message) {
        String messageBody = message.serialize();
        kafkaTemplate.send(TOPIC_AUDIT_LOG, messageBody);
        return SendResult.success();
    }

}
