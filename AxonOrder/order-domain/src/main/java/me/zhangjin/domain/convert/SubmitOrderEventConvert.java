package me.zhangjin.domain.convert;


import me.zhangjin.domain.command.linea.LineASubmitOrderCommand;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.LineASubmitOrderEvent;


public class SubmitOrderEventConvert {
    public static LineASubmitOrderEvent convert(LineASubmitOrderCommand command) {

        // 将 Command 转换为 Event
        LineASubmitOrderEvent event = new LineASubmitOrderEvent();
        event.setProcessType(command.getProcessType());
        event.setOrderStatus(OrderStatus.Submit);
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());

        return event;
    }
}
