package me.zhangjin.domain.bus;

import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;

public class  CommonMessageBus<T> {

    private MBassador<T> bus;

    public CommonMessageBus() {
        bus = new MBassador<>();
    }

    public void subscribe(Object listener) {
        bus.subscribe(listener);
    }

    public void publish(T message) {
        publishWithResult(message);
    }

    public IMessagePublication publishWithResult(T message) {
        IMessagePublication result = bus.publish(message);
        if (result.hasError()) {
            throw new RuntimeException((result.getError().getCause()));
        }
        return result;
    }

}
