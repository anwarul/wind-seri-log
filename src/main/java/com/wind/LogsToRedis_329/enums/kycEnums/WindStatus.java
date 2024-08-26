package com.wind.LogsToRedis_329.enums.kycEnums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum WindStatus {
    INITIATED("Initiated"),
    PENDING("Pending"),
    INCOMPLETE("Incomplete"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    RETRY("Retry"),
    BREJECTED("B_Rejected"), //Bridge Rejected
    BRESTRICTED("B_Restricted"),
    BCONTROLLED("B_Controlled");
    private String label;
    private static final Map<String,WindStatus> ENUM_MAP;
    WindStatus(String label) {
        this.label = label;
    }

    static {
        Map<String,WindStatus> map = new ConcurrentHashMap<String, WindStatus>();
        for (WindStatus instance : WindStatus.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static WindStatus get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }

    public static String getMappingStatus(String status){
        WindStatus windStatus=WindStatus.get(status);
    if (windStatus==WindStatus.BCONTROLLED||windStatus==WindStatus.BRESTRICTED){
        status=WindStatus.BREJECTED.getName();
    }
        return status;
    }

}
