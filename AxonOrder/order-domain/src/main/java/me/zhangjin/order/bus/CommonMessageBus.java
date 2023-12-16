package me.zhangjin.order.bus;

import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMessageBus<T> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MBassador<T> bus;

    public CommonMessageBus() {
        bus = new MBassador<>();
    }

    public void subscribe(Object listener) {
        bus.subscribe(listener);
    }

    public boolean unsubscribe(Object listener) {
        return bus.unsubscribe(listener);
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

    public void publishAsync(T message) {
        IMessagePublication result = bus.publish(message);
        if (result.hasError()) {
            throw new BusException(result.getError().getCause());
        }
    }

    public void publishAsyncWithOutError(T message) {
        IMessagePublication result = bus.publish(message);
        if (result.hasError()) {
            logger.error("eventBus-publishAsync fail", result.getError().getCause());
        }
    }

    public IMessagePublication publishAsyncWithResult(T message) {
        return bus.publishAsync(message);
    }
}
