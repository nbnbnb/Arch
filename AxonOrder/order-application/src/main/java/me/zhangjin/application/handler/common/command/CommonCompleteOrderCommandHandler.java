package me.zhangjin.application.handler.common.command;

import me.zhangjin.domain.acl.repository.OrderRepository;
import me.zhangjin.domain.command.common.CommonCompleteOrderCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.types.dto.common.CommonCompleteOrderDTO;
import me.zhangjin.types.exception.BizExceptioin;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonCompleteOrderCommandHandler {

    @Autowired
    private OrderRepository repository;

    @Handler
    public void commonCompleteOrder(CommonCompleteOrderCommand command) {

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
        CommonCompleteOrderDTO res = new CommonCompleteOrderDTO();
        res.setSuccess(true);
        command.setReturnResult(res);
    }

}
