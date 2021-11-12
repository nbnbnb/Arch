package me.zhangjin.bank.messaging;

import me.zhangjin.bank.message.AuditMessage;
import me.zhangjin.bank.types.SendResult;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
