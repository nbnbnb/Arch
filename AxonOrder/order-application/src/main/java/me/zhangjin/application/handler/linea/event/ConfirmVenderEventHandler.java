package me.zhangjin.application.handler.linea.event;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.messaging.MessageTopic;
import me.zhangjin.domain.command.AutoCompleteCommand;
import me.zhangjin.domain.command.DomainCommand;
import me.zhangjin.domain.event.ConfirmVenderEvent;
import me.zhangjin.domain.event.DomainEvent;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConfirmVenderEventHandler {

    @Autowired
    private MessageProducer messageProducer;

    @Handler
    public void sendSMS(ConfirmVenderEvent event) {
        // 发送确认短信
        messageProducer.send(MessageTopic.ORDER_SEND_SMS,buildMessage(event));
    }

    @Handler
    public void sendDelayCompleteMQ(ConfirmVenderEvent event) {
        LocalDateTime delayTime = LocalDateTime.now().minusDays(1);

        AutoCompleteCommand autoCompleteCommand = new AutoCompleteCommand();
        autoCompleteCommand.setOrderId(event.getOrderId());

        // 发送延迟消息
        messageProducer.send(MessageTopic.ORDER_DOMAIN_COMAND_TOPIC, buildMessage(autoCompleteCommand), delayTime);
    }

    private Map<String,Object> buildMessage(DomainCommand domainCommand) {
        Map<String, Object> message = new HashMap<>();
        message.put("orderid", domainCommand.getOrderId());
        message.put("eventtype",domainCommand.getClass().getTypeName());
        message.put("content", JSON.toJSON(domainCommand));
        return message;
    }

    private Map<String,Object> buildMessage(DomainEvent domainEvent) {
        Map<String, Object> message = new HashMap<>();
        message.put("orderId", domainEvent.getOrderId());
        message.put("eventtype",domainEvent.getClass().getTypeName());
        message.put("content", JSON.toJSON(domainEvent));
        return message;
    }

}
