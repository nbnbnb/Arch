package me.zhangjin.application.process;

import me.zhangjin.application.handler.common.command.CommonCompleteOrderCommandHandler;
import me.zhangjin.types.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineBProcessManager extends AbstractProcessManager {

    @Autowired
    private CommonCompleteOrderCommandHandler completeOrderCommandHandler;

    @Override
    public void initialize() {
        registerHandler(completeOrderCommandHandler, 5, Boolean.FALSE);
    }

    @Override
    public ProcessType getProcessType() {
        return ProcessType.LineBProcess;
    }
}
