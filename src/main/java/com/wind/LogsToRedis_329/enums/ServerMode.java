package com.wind.LogsToRedis_329.enums;

public enum ServerMode {
        RUNNING("Running"),
        STOPPED("Stopped"),
        UNDER_MAINTENANCE("Under Maintenance");

        private String label;

        ServerMode(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
