package me.zhangjin.domain.command.common;

import lombok.Getter;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.CompleteOrderDTO;
import me.zhangjin.types.dto.PayOrderDTO;

import java.math.BigDecimal;

@Getter
public class PayOrderCommand extends DomainCommand<PayOrderDTO> {

    private final BigDecimal actualAmount;

    public PayOrderCommand(BigDecimal actualAmount, Long orderId, ProcessType processType) {
        super(orderId, processType);
        this.actualAmount = actualAmount;
    }
}
