package com.ulucasfraga.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.ulucasfraga.BaseWeb;
import com.ulucasfraga.data.dynamic.DeviceProtectionDataFactory;
import com.ulucasfraga.page.device.DeviceProtectionPage;
import org.testng.annotations.Test;

public class DeviceProtectionWebTest extends BaseWeb {

  @Test(description = "Device Protection Information")
  public void selectDeviceProtectionPrice() {
    var deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();

    var deviceProtectionPage = new DeviceProtectionPage();
    deviceProtectionPage.selectDevicePrice(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.finish();

    assertThat(deviceProtectionPage.getDevicePurchaseText()).isEqualTo("THB 10,001 - 15,000");
  }
}
