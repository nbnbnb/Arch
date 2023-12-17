package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.ConfirmVenderDTO;
import me.zhangjin.types.dto.SubmitLineAOrderDTO;

@Getter
@Setter
public class ConfirmVenderCommand extends DomainCommand<ConfirmVenderDTO> {

    private String venderOrderCode;

    private Long venderId;

}
