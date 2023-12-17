package me.zhangjin.domain.convert;


import me.zhangjin.domain.command.linea.SubmitLineAOrderCommand;
import me.zhangjin.domain.entity.OrderStatus;
import me.zhangjin.domain.event.linea.SubmitOrderEvent;


public class SubmitOrderEventConvert {
    public static SubmitOrderEvent convert(SubmitLineAOrderCommand command) {

        // 将 Command 转换为 Event
        SubmitOrderEvent event = new SubmitOrderEvent();
        event.setProcessType(command.getProcessType());
        event.setOrderStatus(OrderStatus.Submit);
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());

        return event;
    }
}
