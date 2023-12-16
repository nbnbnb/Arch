package me.zhangjin.order.domain.entity.convert;

import me.zhangjin.order.command.SubmitOrderCommand;
import me.zhangjin.order.domain.entity.event.SubmitOrderEvent;

public class SubmitOrderEventConvert {
    public static SubmitOrderEvent convert(SubmitOrderCommand command){

        // 将 Command 转换为 Event
        SubmitOrderEvent event = new SubmitOrderEvent();
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());

        return event;
    }
}
