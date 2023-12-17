package me.zhangjin.application.handler.common;

import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.command.common.CompleteOrderCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.types.dto.CompleteOrderDTO;
import me.zhangjin.types.exception.BizExceptioin;
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
        if (order.getOrderStatus() == OrderStatus.Completed) {
            throw new BizExceptioin("1005", "order status is completed");
        }

        if (order.getOrderStatus() == OrderStatus.Canceled) {
            throw new BizExceptioin("1006", "order status is canceled");
        }

        // 03 变更快照状态（内存）
        order.completeOrder(command);

        // 04. 保存最新快照，并发送 MQ
        repository.save(order);

        // 05. 设置返回值
        CompleteOrderDTO res = new CompleteOrderDTO();
        res.setSuccess(true);
        command.setReturnResult(res);
    }

}
