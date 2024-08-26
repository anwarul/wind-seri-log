package com.wind.LogsToRedis_329.enums;

public enum RoleName {
  TEMP_PAYROLL_USER_ROLE("Temp payroll user role");

  private String label;

  RoleName(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public String getName() {
    return name();
  }
}
