package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.CompleteType;
import me.zhangjin.domain.entity.Order;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.CompleteOrderDTO;

@Getter
@Setter
public class CompleteOrderCommand extends DomainCommand<CompleteOrderDTO> {
    // 1 自动完成
    // 2 手动完成
    private CompleteType completeType;
}
