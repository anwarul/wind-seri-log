package com.wind.LogsToRedis_329.enums;

public enum VerificationProviderName {
  VONYAGE("Vonyage"),
  TWILIO("Twilio");

  private String label;

  VerificationProviderName(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public String getName() {
    return name();
  }
}
