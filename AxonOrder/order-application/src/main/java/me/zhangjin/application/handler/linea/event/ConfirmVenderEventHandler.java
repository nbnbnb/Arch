package me.zhangjin.application.handler.linea.event;

import com.alibaba.fastjson.JSON;
import me.zhangjin.domain.acl.messaging.MessageProducer;
import me.zhangjin.domain.acl.messaging.MessageTopic;
import me.zhangjin.domain.command.CompleteOrderCommand;
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
        Map<String,String> data = new HashMap<>();
        data.put("phone","13888888888");
        data.put("content","test test");
        // 发送确认短信
        messageProducer.sendSMS(data);
    }

    @Handler
    public void sendDelayCompleteMQ(ConfirmVenderEvent event) {
        LocalDateTime delayTime = LocalDateTime.now().minusDays(1);

        CompleteOrderCommand autoCompleteCommand = new CompleteOrderCommand();
        autoCompleteCommand.setOrderId(event.getOrderId());

        // 发送延迟消息（DomainCommand）
        messageProducer.sendDomainCommand(autoCompleteCommand, delayTime);
    }



}
