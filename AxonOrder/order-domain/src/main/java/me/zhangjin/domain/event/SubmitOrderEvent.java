package me.zhangjin.domain.event;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.types.ProcessType;

@Setter
@Getter
public class SubmitOrderEvent extends DomainEvent {
    @Override
    public String getDesc() {
        return "Do SubmitOrderEvent";
    }

    private ProcessType processType;
}
