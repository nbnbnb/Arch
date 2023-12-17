package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;

@Getter
public class CompleteOrderCommand extends DomainCommand {
    // 1 自动完成
    // 2 手动完成
    private Integer completeType;

    public CompleteOrderCommand(Integer completeType, Order order) {
        super(order.getOrderId(), order.getProcessType());
        this.completeType = completeType;
    }
}
