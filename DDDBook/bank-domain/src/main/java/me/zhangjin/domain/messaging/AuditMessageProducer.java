package me.zhangjin.domain.messaging;

import me.zhangjin.domain.message.AuditMessage;
import me.zhangjin.domain.types.SendResult;

// 类似 ExchangeRateService 服务的抽象，对各种中间件的抽象的目的是让业务代码不再依赖中间件的实现逻辑
// 因为中间件通常需要有通用型，中间件的接口通常是 String 或 Byte[] 类型的，导致序列化/反序列化逻辑通常和业务逻辑混杂在一起，造成胶水代码
// 通过中间件的 ACL 抽象，减少重复胶水代码

// 在这个案例里，我们通过封装一个抽象的 AuditMessageProducer 和 AuditMessage DP 对象，实现对底层 kafka 实现的隔离
public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
