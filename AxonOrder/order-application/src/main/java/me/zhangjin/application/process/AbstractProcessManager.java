package me.zhangjin.application.process;

import me.zhangjin.application.bus.CommonMessageBus;
import me.zhangjin.types.ProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractProcessManager implements InitializingBean {

    private List<BusWrapper> buses;

    public AbstractProcessManager() {
        buses = new ArrayList<>(2);
    }

    public void dispatch(Object message) {
        buses.stream().sorted(Comparator.comparing(BusWrapper::getPriority)).forEach(m -> m.publish(message));
    }

    /**
     * 注册同步执行器
     *
     * @param handler         执行器
     * @param priority        优先级
     * @param ignoreException 是否忽略异常
     */
    protected void registerHandler(Object handler, Integer priority, Boolean ignoreException) {
        BusWrapper busWrapper = new BusWrapper();
        CommonMessageBus bus = new CommonMessageBus();
        bus.subscribe(handler);
        busWrapper.setBus(bus);
        busWrapper.setPriority(priority);
        busWrapper.setIgnoreException(ignoreException);

        buses.add(busWrapper);
    }

    public abstract void initialize();

    public abstract ProcessType getProcessType();

    @Override
    public void afterPropertiesSet() {
        initialize();
    }

    private static class BusWrapper {
        private Integer priority;
        private Boolean ignoreException;
        private CommonMessageBus bus;

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        @SuppressWarnings("unchecked")
        public void publish(Object message) {
            try {
                bus.publish(message);
            } catch (Exception ex) {
                if (!ignoreException) {
                    throw ex;
                }
            }
        }

        private void setIgnoreException(Boolean ignoreException) {
            this.ignoreException = ignoreException;
        }

        private void setBus(CommonMessageBus bus) {
            this.bus = bus;
        }
    }
}

