package me.zhangjin.domain.entity;

import lombok.Getter;
import me.zhangjin.domain.convert.ConfirmVenderEventConvert;
import me.zhangjin.domain.convert.SendVenderEventConvert;
import me.zhangjin.domain.convert.SubmitOrderEventConvert;
import me.zhangjin.domain.event.ConfirmVenderEvent;
import me.zhangjin.domain.event.SendVenderEvent;
import me.zhangjin.domain.event.SubmitOrderEvent;
import me.zhangjin.types.ProcessType;
import me.zhangjin.domain.command.ConfirmVenderCommand;
import me.zhangjin.domain.command.SendVenderCommand;
import me.zhangjin.domain.command.SubmitOrderCommand;


@Getter
public class Order extends RootEntity {

    private Integer orderStatus;

    private Long orderId;

    private ProcessType processType;

    private String venderOrderCode;

    private Long venderId;

    public void submitOrder(SubmitOrderCommand command) {

        // 将 Command 转换为 Event
        // 修改内存中的状态（调用符合签名的 when 方法）
        apply(SubmitOrderEventConvert.convert(command));
    }

    public void sendVender(SendVenderCommand command) {
        apply(SendVenderEventConvert.convert(command));
    }

    public void confirmVender(ConfirmVenderCommand command) {
        apply(ConfirmVenderEventConvert.convert(command));
    }

    // region when 处理方法：强制要求 - 所有对 Order 数据的修改，都在 when 方法中执行

    private void when(SubmitOrderEvent event) {

        // Order 初始化

        this.orderId = event.getOrderId();

        // 设置订单状态为已提交
        this.orderStatus = event.getOrderStatus().getCode();

        // 获取流程类型
        this.processType = event.getProcessType();

    }

    private void when(SendVenderEvent event) {
        // 设置订单状态为已下单
        this.orderStatus = event.getOrderStatus().getCode();
    }

    private void when(ConfirmVenderEvent event) {
        // 设置订单状态为已下单
        this.orderStatus = event.getOrderStatus().getCode();
        this.venderId = event.getVenderId();
        this.venderOrderCode = event.getVenderOrderCode();
    }

    // endregion

}
