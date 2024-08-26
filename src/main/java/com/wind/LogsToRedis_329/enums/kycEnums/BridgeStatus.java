package com.wind.LogsToRedis_329.enums.kycEnums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum BridgeStatus {
    INCOMPLETE("Incomplete"),
    NOT_STARTED("Not Started"),
    PENDING("Pending"),
    UNDER_REVIEW("Under Review"),
    APPROVED("Approved"),
    REJECTED("Rejected");
    private final String label;

    BridgeStatus(String label) {
        this.label = label;
    }

    private static final Map<String, BridgeStatus> ENUM_MAP;

    static {
        Map<String, BridgeStatus> map = new ConcurrentHashMap<String, BridgeStatus>();
        for (BridgeStatus instance : BridgeStatus.values()) {
            map.put(instance.getName().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static BridgeStatus get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }

    public static String getNameFromLabel(String label) {
        for (BridgeStatus status : BridgeStatus.values()) {
            if (status.label.equals(label)) {
                return status.getName();
            }
        }
        return null;
    }

}
