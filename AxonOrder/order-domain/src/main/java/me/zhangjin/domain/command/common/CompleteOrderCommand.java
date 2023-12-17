package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteOrderCommand extends DomainCommand {
    // 1 自动完成
    // 2 手动完成
    private Integer completeType;
}
