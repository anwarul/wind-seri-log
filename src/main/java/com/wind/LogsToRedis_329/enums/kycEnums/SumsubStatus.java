package com.wind.LogsToRedis_329.enums.kycEnums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SumsubStatus {
    INITIATED("Initiated"),
    PENDING("Pending"),
    INCOMPLETE("Incomplete"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    RETRY("Retry"),
    ON_HOLD("On Hold");
    private final String label;

    SumsubStatus(String label) {
        this.label = label;
    }

    private static final Map<String, SumsubStatus> ENUM_MAP;

    static {
        Map<String, SumsubStatus> map = new ConcurrentHashMap<String, SumsubStatus>();
        for (SumsubStatus instance : SumsubStatus.values()) {
            map.put(instance.getName().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static SumsubStatus get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }
}