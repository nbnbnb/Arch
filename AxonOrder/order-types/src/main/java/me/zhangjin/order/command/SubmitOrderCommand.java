package me.zhangjin.order.command;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.order.types.ProcessType;

@Getter
@Setter
public class SubmitOrderCommand extends AbstractCommand {

    private ProcessType processType;

}
