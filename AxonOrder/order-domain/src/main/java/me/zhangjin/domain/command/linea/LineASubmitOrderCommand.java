package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.types.dto.linea.LineASubmitOrderDTO;

@Getter
@Setter
public class LineASubmitOrderCommand extends DomainCommand<LineASubmitOrderDTO> {
    private String uid;
}
