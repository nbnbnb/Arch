package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.SubmitLineAOrderDTO;

@Getter
@Setter
public class SubmitLineAOrderCommand extends DomainCommand<SubmitLineAOrderDTO> {

    private String uid;

    public SubmitLineAOrderCommand(Long orderId, ProcessType processType) {
        super(orderId, processType);
    }

}
