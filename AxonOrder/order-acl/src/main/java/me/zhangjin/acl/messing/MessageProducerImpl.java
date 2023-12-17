package me.zhangjin.acl.messing;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.logger.OrderLogger;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.messaging.MessageTopic;
import me.zhangjin.domain.command.DomainCommand;
import me.zhangjin.domain.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageProducerImpl implements MessageProducer {

    @Autowired
    private OrderLogger orderLogger;


    private void send(String topic, Map<String, String> data) {
        orderLogger.info("send message: %s %s", topic, JSON.toJSONString(data));
    }


    private void send(String topic, Map<String, String> data, LocalDateTime delayTime) {
        orderLogger.info("send delay message: %s %s at %s", topic, JSON.toJSONString(data), delayTime);
    }

    @Override
    public void sendDomainEvent(DomainEvent domainEvent, LocalDateTime delayTime) {
        Map<String, String> message = buildMessage(domainEvent.getOrderId(), domainEvent);
        this.send(MessageTopic.ORDER_DOMAIN_EVENT_TOPIC, message, delayTime);
    }

    @Override
    public void sendDomainCommand(DomainCommand domainCommand, LocalDateTime delayTime) {
        Map<String, String> message = buildMessage(domainCommand.getOrderId(), domainCommand);
        this.send(MessageTopic.ORDER_DOMAIN_COMAND_TOPIC, message, delayTime);
    }

    @Override
    public void sendEmail(Map<String, String> data) {
        this.send(MessageTopic.ORDER_SEND_EMAIL, data);
    }

    @Override
    public void sendSMS(Map<String, String> data) {
        this.send(MessageTopic.ORDER_SEND_SMS, data);
    }

    @Override
    public void sendVendor(Map<String, String> data) {
        this.send(MessageTopic.ORDER_SEND_VENDER, data);
    }

    @Override
    public void sendDomainEvent(DomainEvent domainEvent) {
        Map<String, String> message = buildMessage(domainEvent.getOrderId(), domainEvent);
        this.send(MessageTopic.ORDER_DOMAIN_COMAND_TOPIC, message);
    }

    @Override
    public void sendDomainCommand(DomainCommand domainCommand) {
        Map<String, String> message = buildMessage(domainCommand.getOrderId(), domainCommand);
        this.send(MessageTopic.ORDER_DOMAIN_COMAND_TOPIC, message);
    }

    private Map<String, String> buildMessage(Long orderId, Object domain) {
        Map<String, String> message = new HashMap<>();
        message.put("orderid", orderId.toString());
        message.put("content", JSON.toJSONString(domain));
        message.put("eventtype", domain.getClass().getTypeName());
        return message;
    }

}
