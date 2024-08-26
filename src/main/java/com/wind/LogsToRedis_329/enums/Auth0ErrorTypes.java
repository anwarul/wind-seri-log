package com.wind.LogsToRedis_329.enums;

public enum Auth0ErrorTypes {
    INCORRECT("Incorrect otp"),
    TIME_OUT("expired otp"),
    TOO_MANY_ATTEMPTS("Too many attempts"),
    UNKNOWN("Unknown error");

    private String label;

    Auth0ErrorTypes(String label) {
    this.label = label;
    }

    public String getLabel() {
    return label;
    }

    public String getName() {
    return name();
    }
}
