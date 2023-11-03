package com.ulucasfraga.model;

public record DeviceProtection(String deviceProtectionPrice) {

  public static final class DeviceProtectionBuilder {

    private String deviceProtectionPrice;

    public DeviceProtectionBuilder devicePrice(String deviceProtectionPrice) {
      this.deviceProtectionPrice = deviceProtectionPrice;
      return this;
    }

    public DeviceProtection build() {
      return new DeviceProtection(deviceProtectionPrice);
    }
  }
}
