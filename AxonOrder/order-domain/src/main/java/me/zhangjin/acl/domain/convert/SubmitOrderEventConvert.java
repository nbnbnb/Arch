package me.zhangjin.acl.domain.convert;

import me.zhangjin.acl.command.SubmitOrderCommand;
import me.zhangjin.acl.domain.event.SubmitOrderEvent;

public class SubmitOrderEventConvert {
    public static SubmitOrderEvent convert(SubmitOrderCommand command){

        // 将 Command 转换为 Event
        SubmitOrderEvent event = new SubmitOrderEvent();
        event.setOrderId(command.getOrderId());
        event.setProcessType(command.getProcessType());

        return event;
    }
}
