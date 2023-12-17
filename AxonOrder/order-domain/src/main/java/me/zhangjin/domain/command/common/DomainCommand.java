package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DomainCommand {
    protected Long orderId;
}
