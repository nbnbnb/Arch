package me.zhangjin.application.process;

import me.zhangjin.application.handler.linea.command.CompleteOrderCommandHandler;
import me.zhangjin.application.handler.linea.command.ConfirmVenderCommandHandler;
import me.zhangjin.application.handler.linea.command.SubmitOrderCommandHandler;
import me.zhangjin.application.handler.linea.event.ConfirmVenderEventHandler;
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

    @Autowired
    private ConfirmVenderEventHandler confirmVenderEventHandler;

    @Autowired
    private ConfirmVenderCommandHandler confirmVenderCommandHandler;

    @Autowired
    private CompleteOrderCommandHandler completeOrderCommandHandler;

    @Override
    public void initialize() {
        registerHandler(submitOrderCommandHandler, 1, Boolean.FALSE);
        registerHandler(submitOrderEventHandler, 2, Boolean.FALSE);
        registerHandler(confirmVenderEventHandler, 3, Boolean.FALSE);
        registerHandler(confirmVenderCommandHandler, 4, Boolean.FALSE);
        registerHandler(completeOrderCommandHandler, 5, Boolean.FALSE);
    }

    @Override
    public ProcessType getProcessType() {
        return ProcessType.LineAProcess;
    }
}
