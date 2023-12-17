package me.zhangjin.domain.convert;

import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.ConfirmVenderEvent;
import me.zhangjin.domain.command.linea.ConfirmVenderCommand;

public class ConfirmVenderEventConvert {

    public static ConfirmVenderEvent convert(ConfirmVenderCommand command) {
        ConfirmVenderEvent confirmVenderEvent = new ConfirmVenderEvent(command.getOrderId(), command.getProcessType());
        confirmVenderEvent.setVenderId(command.getVenderId());
        confirmVenderEvent.setVenderOrderCode(confirmVenderEvent.getVenderOrderCode());
        confirmVenderEvent.setOrderStatus(OrderStatus.ConfirmOrder);

        return confirmVenderEvent;
    }

}
