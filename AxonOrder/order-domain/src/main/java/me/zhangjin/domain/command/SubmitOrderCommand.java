package me.zhangjin.domain.command;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.ProcessType;

@Getter
@Setter
public class SubmitOrderCommand extends DomainCommand {

    private ProcessType processType;

}
