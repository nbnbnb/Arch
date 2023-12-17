package me.zhangjin.acl.types;

import java.util.HashMap;
import java.util.Map;


public enum ProcessType {

    // 用于区分不同的业务流程
    Line01Process("Line01 Process"),
    Line02Process("Line02 Process");

    private final static Map<String, ProcessType> PROCESS_TYPE_MAP = new HashMap<>();

    static {
        PROCESS_TYPE_MAP.put(ProcessType.Line01Process.toString(), ProcessType.Line01Process);
        PROCESS_TYPE_MAP.put(ProcessType.Line02Process.toString(), ProcessType.Line02Process);
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
