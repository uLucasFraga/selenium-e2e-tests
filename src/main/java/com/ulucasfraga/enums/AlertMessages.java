package com.ulucasfraga.enums;

import java.security.SecureRandom;
import java.util.function.Supplier;

public enum AlertMessages implements Supplier<String> {
  ALERT_DEVICE_MORE_MONTHS("Sorry, we are unable to offer this plan for devices more than 18 months old."),
  ALERT_DEVICE_DAMAGE("Sorry, we are unable to offer this plan for devices with damage or a cracked screen."),
  ALERT_INVALID_IMEI("Please provide 15-digit IMEI");

  private final String value;

  AlertMessages(String value) {
    this.value = value;
  }

  public static AlertMessages getRandom() {
    return values()[new SecureRandom().nextInt(values().length)];
  }

  @Override
  public String get() {
    return this.value;
  }
}
