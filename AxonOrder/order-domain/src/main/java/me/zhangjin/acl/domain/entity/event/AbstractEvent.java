package me.zhangjin.acl.domain.entity.event;

public abstract class AbstractEvent {

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

