package com.wind.LogsToRedis_329.enums;

public enum AppTheme {
    DARK("Dark"),
    DEFAULT("Default");
    private String label;

    AppTheme(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }
}
