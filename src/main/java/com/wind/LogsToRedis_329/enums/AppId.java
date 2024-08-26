package com.wind.LogsToRedis_329.enums;

public enum AppId {
        WIND_MOBILE("WIND Mobile"),
        PAYROLL("Payroll"),
        PLATFORM("Platform"),
        PLATFORM_DASHBOARD("Platform Dashboard");
        private String label;

        AppId(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
