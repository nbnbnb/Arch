package me.zhangjin.domain.convert;


import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.SubmitOrderEvent;
import me.zhangjin.domain.command.SubmitOrderCommand;


public class SubmitOrderEventConvert {
    public static SubmitOrderEvent convert(SubmitOrderCommand command){

        // 将 Command 转换为 Event
        SubmitOrderEvent event = new SubmitOrderEvent();
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());
        event.setOrderStatus(OrderStatus.Submit);

        return event;
    }
}
