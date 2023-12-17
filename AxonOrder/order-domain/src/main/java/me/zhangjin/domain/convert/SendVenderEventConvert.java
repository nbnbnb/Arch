package me.zhangjin.domain.convert;


import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.LineASendVenderEvent;
import me.zhangjin.domain.command.linea.LineASendVenderCommand;


public class SendVenderEventConvert {
    public static LineASendVenderEvent convert(LineASendVenderCommand command){

        // 将 Command 转换为 Event
        LineASendVenderEvent event = new LineASendVenderEvent();
        event.setOrderStatus(OrderStatus.SendOrder);
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());

        return event;
    }
}
