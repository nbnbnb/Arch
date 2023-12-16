package me.zhangjin.order.bus;

import me.zhangjin.order.command.AbstractCommand;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  CommonMessageBus<T> {

    private MBassador<T> bus;

    public CommonMessageBus() {
        bus = new MBassador<>();
    }

    public void subscribe(Object listener) {
        bus.subscribe(listener);
    }


    public void publish(T message) {
        IMessagePublication result = bus.publish(message);
        if (result.hasError()) {
            throw new BusException(result.getError().getCause());
        }
    }

    public IMessagePublication publishWithResult(T message) {
        return bus.publish(message);
    }

}
