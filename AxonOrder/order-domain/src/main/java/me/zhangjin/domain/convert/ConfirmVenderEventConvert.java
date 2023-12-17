package me.zhangjin.domain.convert;

import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.LineAConfirmVenderEvent;
import me.zhangjin.domain.command.linea.LineAConfirmVenderCommand;

public class ConfirmVenderEventConvert {

    public static LineAConfirmVenderEvent convert(LineAConfirmVenderCommand command) {
        LineAConfirmVenderEvent confirmVenderEvent = new LineAConfirmVenderEvent();
        confirmVenderEvent.setVenderId(command.getVenderId());
        confirmVenderEvent.setVenderOrderCode(command.getVenderOrderCode());
        confirmVenderEvent.setOrderStatus(OrderStatus.ConfirmOrder);
        confirmVenderEvent.setOrderId(command.getOrderId());
        confirmVenderEvent.setProcessType(command.getProcessType());

        return confirmVenderEvent;
    }

}
