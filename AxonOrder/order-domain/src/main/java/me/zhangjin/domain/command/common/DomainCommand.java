package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.common.BaseDTO;

@Getter
@Setter
public abstract class DomainCommand<T extends BaseDTO> {

    protected Long orderId;

    protected ProcessType processType;

    // DomainCommand 执行，都应该有一个返回值
    protected T returnResult;

}
