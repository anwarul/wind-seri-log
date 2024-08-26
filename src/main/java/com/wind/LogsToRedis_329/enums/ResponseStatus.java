package com.wind.LogsToRedis_329.enums;

public enum ResponseStatus {
        SUCCESS("success"),
        FAILED("failed");
        private String label;

        ResponseStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
