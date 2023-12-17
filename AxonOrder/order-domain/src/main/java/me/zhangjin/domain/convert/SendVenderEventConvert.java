package me.zhangjin.domain.convert;


import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.SendVenderEvent;
import me.zhangjin.domain.command.SendVenderCommand;


public class SendVenderEventConvert {
    public static SendVenderEvent convert(SendVenderCommand command){

        // 将 Command 转换为 Event
        SendVenderEvent event = new SendVenderEvent();
        event.setOrderId(command.getOrderId());
        event.setOrderStatus(OrderStatus.SendOrder);

        return event;
    }
}
