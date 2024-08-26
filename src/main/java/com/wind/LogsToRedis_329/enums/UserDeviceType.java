package com.wind.LogsToRedis_329.enums;

public enum UserDeviceType {
    ANDROID("Android"),
    IOS("IOS");

    private String label;

    UserDeviceType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }
}
