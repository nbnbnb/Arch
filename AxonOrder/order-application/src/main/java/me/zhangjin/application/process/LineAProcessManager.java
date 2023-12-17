package me.zhangjin.application.process;

import me.zhangjin.application.handler.linea.command.SubmitOrderCommandHandler;
import me.zhangjin.application.handler.linea.event.SubmitOrderEventHandler;
import me.zhangjin.types.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineAProcessManager extends AbstractProcessManager {

    @Autowired
    private SubmitOrderCommandHandler submitOrderCommandHandler;

    @Autowired
    private SubmitOrderEventHandler submitOrderEventHandler;

    @Override
    public void initialize() {
        registerHandler(submitOrderCommandHandler, 1, Boolean.FALSE);
        registerHandler(submitOrderEventHandler, 2, Boolean.FALSE);
    }

    @Override
    public ProcessType getProcessType() {
        return ProcessType.LineAProcess;
    }
}
