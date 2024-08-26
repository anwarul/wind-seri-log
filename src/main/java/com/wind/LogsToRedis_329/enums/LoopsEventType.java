package com.wind.LogsToRedis_329.enums;

public enum LoopsEventType {
    SIGNUP_COMPLETED("Signup completed"),
    SIGNUP_VERIFIED("Signup verified"),
    ADD_FUNDS("Add funds"),
    TRANSFER_FUNDS("Transfer funds"),
    CASHOUT("Cashout"),
    KYC_VERIFICATION_STARTED("KYC verification started"),
    KYC_VERIFICATION_SUBMITTED("KYC verification submitted"),
    KYC_VERIFIED("KYC verified"),
    ONRAMP_ORDER_CREATED("On-ramp order created"),
    BUSINESS_SIGNUP_COMPLETED("Business signup completed"),
    BUSINESS_SIGNUP_VERIFIED("Business signup verified"),
    BUSINESS_TOP_UP_COMPLETED("Business top up completed"),
    BUSINESS_PAYROLL_PAYOUT_COMPLETED("Business payroll payout completed"),
    TEST_EVENT("Test event");

    private String label;

    LoopsEventType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name();
    }
}
