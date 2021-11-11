package me.zhangjin.bank.messaging;

import me.zhangjin.bank.types.AuditMessage;
import me.zhangjin.bank.types.SendResult;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
