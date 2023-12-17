package me.zhangjin.domain.entity;

import lombok.Getter;
import me.zhangjin.domain.command.common.CompleteOrderCommand;
import me.zhangjin.domain.command.linea.SubmitLineAOrderCommand;
import me.zhangjin.domain.convert.CompleteOrderEventConvert;
import me.zhangjin.domain.convert.ConfirmVenderEventConvert;
import me.zhangjin.domain.convert.SendVenderEventConvert;
import me.zhangjin.domain.convert.SubmitOrderEventConvert;
import me.zhangjin.domain.event.common.CompleteOrderEvent;
import me.zhangjin.domain.event.linea.ConfirmVenderEvent;
import me.zhangjin.domain.event.linea.SendVenderEvent;
import me.zhangjin.domain.event.linea.SubmitOrderEvent;
import me.zhangjin.types.ProcessType;
import me.zhangjin.domain.command.linea.ConfirmVenderCommand;
import me.zhangjin.domain.command.linea.SendVenderCommand;

import java.time.LocalDateTime;


@Getter
public class Order extends RootEntity {

    private Integer orderStatus;

    private Long orderId;

    private ProcessType processType;

    private String venderOrderCode;

    private Long venderId;

    // 1 自动完成
    // 2 手动完成
    private Integer completeType;

    private LocalDateTime completeTime;

    // 订单数据
    
    // coupon
    // userinfo
    // priceinfo
    // xinfo

    // region 通过公共方法，修改 Entity 状态，方法名有明确的业务含义
    public void submitOrder(SubmitLineAOrderCommand command) {
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

    public void completeOrder(CompleteOrderCommand command) {
        apply(CompleteOrderEventConvert.convert(command));
    }

    // endregion

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

    private void when(CompleteOrderEvent event) {
        // 设置为完成状态
        this.orderStatus = event.getOrderStatus().getCode();
        this.completeTime = event.getCompleteTime();
        this.completeType = event.getCompleteType();
    }

    // endregion

}
