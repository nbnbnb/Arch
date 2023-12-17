package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.dto.common.BaseDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class CommonPayOrderCommand extends DomainCommand<BaseDTO> {
    private  BigDecimal actualAmount;
}
