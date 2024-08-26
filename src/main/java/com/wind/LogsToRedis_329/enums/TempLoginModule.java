package com.wind.LogsToRedis_329.enums;

public enum TempLoginModule {
        RECIPIENT_VERIFICATION("Recipient verification");
        private String label;

        TempLoginModule(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
