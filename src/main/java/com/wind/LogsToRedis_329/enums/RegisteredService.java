package com.wind.LogsToRedis_329.enums;

public enum RegisteredService {
  WIND_JAVA("windjava"),
  WIND_WEB3("windweb3"),
  NOTIFICATION_SERVICE("notificationService"),
  WIND_SOCKET("windSocket");

  private String label;

  RegisteredService(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public String getName() {
    return name();
  }
}
