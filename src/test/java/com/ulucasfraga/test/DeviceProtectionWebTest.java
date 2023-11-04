package com.ulucasfraga.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import com.ulucasfraga.BaseWeb;
import com.ulucasfraga.data.dynamic.DeviceProtectionDataFactory;
import com.ulucasfraga.page.device.DeviceProtectionPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class DeviceProtectionWebTest extends BaseWeb {

  @Test(description = "Device Protection Information - Random Device Price")
  public void randomDevicePrice() {
    var deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();
    var deviceProtectionPage = new DeviceProtectionPage();

    deviceProtectionPage.confirmYourDevicePrice();
    deviceProtectionPage.selectDevicePrice(deviceProtectionData.deviceProtectionPrice());
    assertThat(deviceProtectionPage.getDevicePrice())
        .isEqualTo(deviceProtectionData.deviceProtectionPrice());
  }

  @Test(description = "Device Protection Information - The Same Device Price")
  public void sameDevicePrice() {
    var deviceProtectionPage = new DeviceProtectionPage();

    deviceProtectionPage.confirmYourDevicePrice();
    deviceProtectionPage.selectDevicePrice("THB 10,001 - 15,000");
    assertThat(deviceProtectionPage.getDevicePrice()).isEqualTo("THB 10,001 - 15,000");
  }

  // TODO: Device Protection - To Change The Plan Of Device Price And Price Per Month Table
  // very difficult to do this validation for now
  @Ignore()
  public void changePlanDevicePrice() {
    var deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();
    var deviceProtectionPage = new DeviceProtectionPage();

    deviceProtectionPage.confirmYourDevicePrice();
    deviceProtectionPage.selectDevicePrice(deviceProtectionData.deviceProtectionPrice());
    String valuePlanDevice = deviceProtectionPage.getValuePlanDevicePrice();
    assertThat(deviceProtectionPage.getValuePlanDevicePrice()).isEqualTo(valuePlanDevice);
    //    assertThat(deviceProtectionPage.getValuePricePerMonthTable(valuePlanDevice))
    //        .isEqualTo(valuePlanDevice);
  }

  @Test(description = "Device Protection Information - Analyze Final Price")
  public void analyzeFinalPrice() {
    var deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();
    var deviceProtectionPage = new DeviceProtectionPage();

    deviceProtectionPage.confirmYourDevicePrice();
    deviceProtectionPage.selectDevicePrice(deviceProtectionData.deviceProtectionPrice());
    String valuePlanDevice = deviceProtectionPage.getValuePlanDevicePrice();
    deviceProtectionPage.selectPaymentSummaryButton();
    assertThat(deviceProtectionPage.getValueFinalPrice()).isEqualTo(valuePlanDevice + ".00");
  }

  @Test(description = "Device Protection Information - Analyze Content And URL")
  public void analyzeContentAndURL() {
    var deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();
    var deviceProtectionPage = new DeviceProtectionPage();

    deviceProtectionPage.confirmYourDevicePrice();
    deviceProtectionPage.selectDevicePrice(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectPaymentSummaryButton();
    String utmSource = deviceProtectionPage.getUtmSource();
    assertTrue(deviceProtectionPage.checkContentAndURL());
    assertThat(utmSource).isNotEmpty();
  }
}
