package com.wind.LogsToRedis_329.enums;

public enum UserStatus {
        ACTIVE("Active"),
        PENDING("Pending"),
        SUSPENDED("Suspended"),
        INACTIVE("Inactive"),
        DELETED("Deleted");

        private String label;

        UserStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
