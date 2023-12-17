package me.zhangjin.types.command;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.command.AbstractCommand;
import me.zhangjin.types.ProcessType;

@Getter
@Setter
public class SubmitOrderCommand extends AbstractCommand {

    private ProcessType processType;

}
