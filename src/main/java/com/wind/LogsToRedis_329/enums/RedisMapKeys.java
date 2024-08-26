package com.wind.LogsToRedis_329.enums;

public enum RedisMapKeys {
    USER_SIGN_IN("User wrong Sign in Attempt"),
    USER_ALLOWED_URL("User allowed url"),
    DEVELOPER_SECRETS("Developer Secrets"),
    SIGNUP_OTP_ATTEMPT("Sign up OTP attempt");

        private String label;

        RedisMapKeys(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public String getName() {
            return name();
        }
}
