package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.types.dto.linea.LineAConfirmVenderDTO;

@Getter
@Setter
public class LineAConfirmVenderCommand extends DomainCommand<LineAConfirmVenderDTO> {

    private String venderOrderCode;

    private Long venderId;

}
