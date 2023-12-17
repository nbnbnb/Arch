package me.zhangjin.domain.command.linea;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.common.DomainCommand;
import me.zhangjin.types.ProcessType;

@Getter
@Setter
public class SubmitOrderCommand extends DomainCommand {

    private ProcessType processType;

}
