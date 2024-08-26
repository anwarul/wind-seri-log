package com.wind.LogsToRedis_329.enums;

public enum VerificationTokenStatus {
    PENDING("pending"), // unopened token
    USED("used token"), //  used token
    EXPIRED("expired");

    private String label;

    VerificationTokenStatus(String label) {
    this.label = label;
    }

    public String getLabel() {
    return label;
    }

    public String getName() {
    return name();
    }
}
