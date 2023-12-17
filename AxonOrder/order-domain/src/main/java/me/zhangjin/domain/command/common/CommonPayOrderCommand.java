package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.dto.common.CommonPayOrderDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class CommonPayOrderCommand extends DomainCommand<CommonPayOrderDTO> {
    private  BigDecimal actualAmount;
}
