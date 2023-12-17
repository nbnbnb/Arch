package me.zhangjin.domain.acl.messaging;

import me.zhangjin.domain.command.DomainCommand;
import me.zhangjin.domain.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.Map;

public interface MessageProducer {
    void sendEmail(Map<String, String> data);

    void sendSMS(Map<String, String> data);

    void sendVendor(Map<String, String> data);

    void sendDomainEvent(DomainEvent domainEvent);

    void sendDomainEvent(DomainEvent domainEvent, LocalDateTime delayTime);

    void sendDomainCommand(DomainCommand domainCommand);

    void sendDomainCommand(DomainCommand domainCommand, LocalDateTime delayTime);
}
