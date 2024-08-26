package com.wind.LogsToRedis_329.enums;

public enum RoleType {
  ROLE_SUPER_ADMIN("Super Admin"),
  ROLE_USER("User"),
  ROLE_ADMIN("Admin");

  private String label;

  RoleType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public String getName() {
    return name();
  }
}
