package me.zhangjin.domain.command.linea;

import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;

public class SendVenderCommand extends DomainCommand {

    public SendVenderCommand(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

}
