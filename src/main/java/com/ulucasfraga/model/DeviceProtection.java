package com.ulucasfraga.model;

public record DeviceProtection(String deviceProtectionPrice, String imei) {

  public static final class DeviceProtectionBuilder {

    private String deviceProtectionPrice;
    private String imei;

    public DeviceProtectionBuilder devicePrice(String deviceProtectionPrice) {
      this.deviceProtectionPrice = deviceProtectionPrice;
      return this;
    }

    public DeviceProtectionBuilder imei(String imei) {
      this.imei = imei;
      return this;
    }

    public DeviceProtection build() {
      return new DeviceProtection(deviceProtectionPrice, imei);
    }
  }
}
