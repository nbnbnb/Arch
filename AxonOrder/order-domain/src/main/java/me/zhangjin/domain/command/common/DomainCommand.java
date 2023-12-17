package me.zhangjin.domain.command.common;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.ProcessType;
import me.zhangjin.types.dto.BaseDTO;

@Getter
@Setter
public abstract class DomainCommand<T extends BaseDTO> {

    protected Long orderId;

    protected ProcessType processType;

    protected T returnResult;

    public DomainCommand(Long orderId, ProcessType processType) {
        this.orderId = orderId;
        this.processType = processType;
    }
}
