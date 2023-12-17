package me.zhangjin.types;

import java.util.HashMap;
import java.util.Map;


public enum ProcessType {

    // 用于区分不同的业务流程
    LineAProcess("LineA Process"),
    LineBProcess("LineB Process");

    private final static Map<String, ProcessType> PROCESS_TYPE_MAP = new HashMap<>();

    static {
        PROCESS_TYPE_MAP.put(ProcessType.LineAProcess.toString(), ProcessType.LineAProcess);
        PROCESS_TYPE_MAP.put(ProcessType.LineBProcess.toString(), ProcessType.LineBProcess);
    }

    private String desc;

    ProcessType(String desc) {
        this.desc = desc;
    }

    public static ProcessType of(String processName) {
        return PROCESS_TYPE_MAP.get(processName);
    }

    public String getDesc() {
        return desc;
    }
}
