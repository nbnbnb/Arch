package me.zhangjin.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCommand {
    protected Long orderId;
}