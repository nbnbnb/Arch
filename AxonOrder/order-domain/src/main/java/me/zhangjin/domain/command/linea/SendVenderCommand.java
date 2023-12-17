package me.zhangjin.domain.command.linea;

import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.SendVenderDTO;
import me.zhangjin.types.dto.SubmitLineAOrderDTO;

public class SendVenderCommand extends DomainCommand<SendVenderDTO> {
    public SendVenderCommand(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

}
