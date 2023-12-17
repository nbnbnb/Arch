package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.CompleteOrderDTO;
import me.zhangjin.types.dto.PayOrderDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class PayOrderCommand extends DomainCommand<PayOrderDTO> {
    private  BigDecimal actualAmount;
}
