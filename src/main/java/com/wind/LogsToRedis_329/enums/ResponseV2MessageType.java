package com.wind.LogsToRedis_329.enums;

public enum ResponseV2MessageType {
    SUCCESS("SUCCESS!"),
    FAILED("ERROR!");

    private String label;

    ResponseV2MessageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }

}
