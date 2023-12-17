package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.domain.entity.CompleteType;
import me.zhangjin.types.dto.common.CommonCompleteOrderDTO;

@Getter
@Setter
public class CommonCompleteOrderCommand extends DomainCommand<CommonCompleteOrderDTO> {
    // 1 自动完成
    // 2 手动完成
    private CompleteType completeType;
}
