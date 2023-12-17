package me.zhangjin.application.process;

import me.zhangjin.application.handler.common.command.CommonCompleteOrderCommandHandler;
import me.zhangjin.application.handler.linea.command.LineAConfirmVenderCommandHandler;
import me.zhangjin.application.handler.linea.command.LineASubmitOrderCommandHandler;
import me.zhangjin.application.handler.linea.event.LineAConfirmVenderEventHandler;
import me.zhangjin.application.handler.linea.event.LineASubmitOrderEventHandler;
import me.zhangjin.types.ProcessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineAProcessManager extends AbstractProcessManager {

    @Autowired
    private LineASubmitOrderCommandHandler submitOrderCommandHandler;

    @Autowired
    private LineASubmitOrderEventHandler submitOrderEventHandler;

    @Autowired
    private LineAConfirmVenderEventHandler confirmVenderEventHandler;

    @Autowired
    private LineAConfirmVenderCommandHandler confirmVenderCommandHandler;

    @Autowired
    private CommonCompleteOrderCommandHandler completeOrderCommandHandler;

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
