package me.zhangjin.application.handler.common;

import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.command.common.CompleteOrderCommand;
import me.zhangjin.domain.entity.Order;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompleteOrderCommandHandler {

    @Autowired
    private OrderRepository repository;

    @Handler
    public void completeOrder(CompleteOrderCommand command) {
        // 01 load
        Order order = repository.load(command.getOrderId());

        // 02. verify
        // 业务逻辑检查

        // 03 变更快照状态（内存）
        order.completeOrder(command);

        // 4. 保存最新快照，并发送 MQ
        repository.save(order);
    }

}
