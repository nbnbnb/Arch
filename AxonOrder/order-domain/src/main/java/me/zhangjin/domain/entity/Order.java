package me.zhangjin.domain.entity;

import lombok.Getter;
import me.zhangjin.domain.command.common.CommonCompleteOrderCommand;
import me.zhangjin.domain.command.linea.LineASubmitOrderCommand;
import me.zhangjin.domain.convert.CompleteOrderEventConvert;
import me.zhangjin.domain.convert.ConfirmVenderEventConvert;
import me.zhangjin.domain.convert.SendVenderEventConvert;
import me.zhangjin.domain.convert.SubmitOrderEventConvert;
import me.zhangjin.domain.event.common.CommonCompleteOrderEvent;
import me.zhangjin.domain.event.linea.LineAConfirmVenderEvent;
import me.zhangjin.domain.event.linea.LineASendVenderEvent;
import me.zhangjin.domain.event.linea.LineASubmitOrderEvent;
import me.zhangjin.types.ProcessType;
import me.zhangjin.domain.command.linea.LineAConfirmVenderCommand;
import me.zhangjin.domain.command.linea.LineASendVenderCommand;

import java.time.LocalDateTime;


@Getter
public class Order extends RootEntity {

    private OrderStatus orderStatus;

    private Long orderId;

    private ProcessType processType;

    private String venderOrderCode;

    private Long venderId;

    private CompleteType completeType;

    private LocalDateTime completeTime;

    // 订单数据

    // coupon
    // userinfo
    // priceinfo
    // xinfo

    // region 通过公共方法，修改 Entity 状态，方法名有明确的业务含义
    public void submitOrder(LineASubmitOrderCommand command) {
        // 将 Command 转换为 Event
        // 修改内存中的状态（调用符合签名的 when 方法）
        apply(SubmitOrderEventConvert.convert(command));
    }

    public void sendVender(LineASendVenderCommand command) {
        apply(SendVenderEventConvert.convert(command));
    }

    public void confirmVender(LineAConfirmVenderCommand command) {
        apply(ConfirmVenderEventConvert.convert(command));
    }

    public void completeOrder(CommonCompleteOrderCommand command) {
        apply(CompleteOrderEventConvert.convert(command));
    }

    // endregion

    // region when 处理方法：强制要求 - 所有对 Order 数据的修改，都在 when 方法中执行

    private void when(LineASubmitOrderEvent event) {

        // Order 初始化

        this.orderId = event.getOrderId();

        // 设置订单状态为已提交
        this.orderStatus = event.getOrderStatus();

        // 获取流程类型
        this.processType = event.getProcessType();

    }

    private void when(LineASendVenderEvent event) {
        // 设置订单状态为已下单
        this.orderStatus = event.getOrderStatus();
    }

    private void when(LineAConfirmVenderEvent event) {
        // 设置订单状态为已下单
        this.orderStatus = event.getOrderStatus();
        this.venderId = event.getVenderId();
        this.venderOrderCode = event.getVenderOrderCode();
    }

    private void when(CommonCompleteOrderEvent event) {
        // 设置为完成状态
        this.orderStatus = event.getOrderStatus();
        this.completeTime = event.getCompleteTime();
        this.completeType = event.getCompleteType();
    }

    // endregion

}
