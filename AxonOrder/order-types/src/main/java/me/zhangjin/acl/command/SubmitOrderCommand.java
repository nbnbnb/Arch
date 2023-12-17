package me.zhangjin.acl.command;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.acl.types.ProcessType;

@Getter
@Setter
public class SubmitOrderCommand extends AbstractCommand {

    private ProcessType processType;

}
