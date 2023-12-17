package me.zhangjin.domain.convert;

import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.ConfirmVenderEvent;
import me.zhangjin.domain.command.ConfirmVenderCommand;

public class ConfirmVenderEventConvert {

    public static ConfirmVenderEvent convert(ConfirmVenderCommand command){
        ConfirmVenderEvent confirmVenderEvent = new ConfirmVenderEvent(command.getOrderId());
        confirmVenderEvent.setVenderId(command.getVenderId());
        confirmVenderEvent.setVenderOrderCode(confirmVenderEvent.getVenderOrderCode());
        confirmVenderEvent.setOrderStatus(OrderStatus.ConfirmOrder);

        return confirmVenderEvent;
    }

}
