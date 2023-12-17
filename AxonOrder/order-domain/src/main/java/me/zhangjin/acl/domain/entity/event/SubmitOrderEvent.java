package me.zhangjin.acl.domain.entity.event;

import lombok.Getter;
import lombok.Setter;
import me.zhangjin.acl.types.ProcessType;

@Setter
@Getter
public class SubmitOrderEvent extends DomainEvent {
    @Override
    public String getDesc() {
        return "Do SubmitOrderEvent";
    }

    private ProcessType processType;
}
