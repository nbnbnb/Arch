package me.zhangjin.order.domain.entity;

import lombok.Getter;
import me.zhangjin.order.command.SubmitOrderCommand;
import me.zhangjin.order.domain.entity.convert.SubmitOrderEventConvert;
import me.zhangjin.order.domain.entity.event.SubmitOrderEvent;
import me.zhangjin.order.domain.entity.event.EventSourceRootEntity;
import me.zhangjin.order.types.ProcessType;

@Getter
public class Order extends EventSourceRootEntity {

    private Integer orderStatus;

    private Long orderId;

    private ProcessType processType;

    public void submitOrder(SubmitOrderCommand command) {

        // 将 Command 转换为 Event
        // 修改内存中的状态（调用符合签名的 when 方法）
        apply(SubmitOrderEventConvert.convert(command));
    }

    // region when 处理方法：强制要求 - 所有对 Order 数据的修改，都在 when 方法中执行

    private void when(SubmitOrderEvent event) {

        // Order 初始化

        this.orderId = event.getOrderId();

        // 设置订单状态为已提交
        this.orderStatus = 1;

        // 获取流程类型
        this.processType = event.getProcessType();

    }

    // endregion

}
