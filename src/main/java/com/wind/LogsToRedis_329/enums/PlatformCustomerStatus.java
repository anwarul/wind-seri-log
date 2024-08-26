package com.wind.LogsToRedis_329.enums;

public enum PlatformCustomerStatus {
    INCOMPLETE("Incomplete"),
    PENDING("Pending"),
    IN_REVIEW("In Review"),
    APPROVED("Approved"),
    REJECTED("Approved"),
    INACTIVE("InActive");

    private String label;

    PlatformCustomerStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }
}
