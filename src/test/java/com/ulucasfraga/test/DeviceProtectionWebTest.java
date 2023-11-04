package com.ulucasfraga.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import com.ulucasfraga.BaseWeb;
import com.ulucasfraga.data.dynamic.DeviceProtectionDataFactory;
import com.ulucasfraga.enums.AlertMessages;
import com.ulucasfraga.model.DeviceProtection;
import com.ulucasfraga.page.checkout.CheckoutPage;
import com.ulucasfraga.page.checkout.deviceDetails.DeviceDetailsPage;
import com.ulucasfraga.page.common.CommonPage;
import com.ulucasfraga.page.common.HelperPage;
import com.ulucasfraga.page.deviceProtection.DeviceProtectionPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeviceProtectionWebTest extends BaseWeb {

  private HelperPage helperPage;
  private DeviceProtectionPage deviceProtectionPage;
  private CommonPage commonPage;
  private CheckoutPage checkoutPage;
  private DeviceDetailsPage deviceDetailsPage;
  private DeviceProtection deviceProtectionData;

  @BeforeMethod
  public void setUp() {
    helperPage = new HelperPage();
    deviceProtectionPage = new DeviceProtectionPage();
    commonPage = new CommonPage();
    checkoutPage = new CheckoutPage();
    deviceDetailsPage = new DeviceDetailsPage();
    deviceProtectionData = DeviceProtectionDataFactory.createDeviceProtectionData();
  }

  @Test(description = "Device Protection - Random Device Price")
  public void randomDevicePriceDeviceProtection() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    assertThat(deviceProtectionPage.getDevicePriceProtection())
        .isEqualTo(deviceProtectionData.deviceProtectionPrice());
    String devicePlanPrice = deviceProtectionPage.getDevicePlanPriceProtectionPage();
    deviceProtectionPage.selectDeviceProtectionButton();
    assertThat(commonPage.getFinalPrice()).isEqualTo(devicePlanPrice + ".00");
  }

  @Test(description = "Device Protection - The Same Device Price")
  public void sameDevicePriceDeviceProtection() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection("THB 10,001 - 15,000");
    assertThat(deviceProtectionPage.getDevicePriceProtection()).isEqualTo("THB 10,001 - 15,000");
    String devicePlanPrice = deviceProtectionPage.getDevicePlanPriceProtectionPage();
    deviceProtectionPage.selectDeviceProtectionButton();
    assertThat(commonPage.getFinalPrice()).isEqualTo(devicePlanPrice + ".00");
  }

  @Test(description = "Device Protection - Analyze Content And URL")
  public void analyzeContentAndURLDeviceProtection() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    String utmSource = deviceProtectionPage.getUtmSourceDeviceProtection();
    assertTrue(deviceProtectionPage.checkContentAndURLDeviceProtection());
    assertThat(utmSource).isNotEmpty();
  }

  @Test(description = "Checkout Protection - Analyze Checkout Page")
  public void analyzeCheckoutProtection() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    String valuePlanDeviceHome = deviceProtectionPage.getDevicePlanPriceProtectionPage();
    String productNameDeviceProtection = deviceProtectionPage.getProductNameDeviceProtection();
    deviceProtectionPage.selectDeviceProtectionButton();
    String productNameCheckout = checkoutPage.getProductNameCheckout();
    String currentData = helperPage.getCurrentDateTimeInThailand();
    assertEquals(productNameDeviceProtection, productNameCheckout);
    assertThat(commonPage.getFinalPrice()).isEqualTo(valuePlanDeviceHome + ".00");
    assertThat(checkoutPage.getProviderNameCheckout()).isEqualTo("bolttech");
    assertThat(checkoutPage.getSubscriptionStartDateCheckout()).isEqualTo(currentData);
    assertThat(checkoutPage.getSubscriptionRenewalCheckout()).isEqualTo("Monthly");
  }

  @Test(description = "Checkout - Device Details - Add Valid IMEI And All Answers As YES")
  public void addValidIMEIAndAllAnswersAsYESDeviceDetails() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    deviceDetailsPage.fillImeiDeviceDetails(deviceProtectionData.imei());
    deviceDetailsPage.selectYesOptionDamageDeviceDetails();
    deviceDetailsPage.selectYesOptionLessDaysDeviceDetails();
    assertTrue(deviceDetailsPage.checkIconDoneDetails());
  }

  @Test(description = "Checkout - Device Details - Purchased on or after October 22, 2023")
  public void addValidIMEIAndPurchasedAfterDataValidDeviceDetails() {
    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    deviceDetailsPage.fillImeiDeviceDetails(deviceProtectionData.imei());
    deviceDetailsPage.selectYesOptionDamageDeviceDetails();
    deviceDetailsPage.selectNoOptionLessDaysDeviceDetails();
    deviceDetailsPage.selectYesOptionLessMonthsDeviceDetails();
    assertTrue(deviceDetailsPage.checkIconDoneDetails());
  }

  @Test(description = "Checkout - Device Details - Error For Older Than 18 Months.")
  public void addValidIMEIAndErrorDevicesOlderMinimumMonthsDeviceDetails() {
    String alert = AlertMessages.ALERT_DEVICE_MORE_MONTHS.get();

    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    deviceDetailsPage.fillImeiDeviceDetails(deviceProtectionData.imei());
    deviceDetailsPage.selectYesOptionDamageDeviceDetails();
    deviceDetailsPage.selectNoOptionLessDaysDeviceDetails();
    deviceDetailsPage.selectNoOptionLessMonthsDeviceDetails();
    assertThat(commonPage.getAlertMessage()).isEqualTo(alert);
  }

  @Test(description = "Checkout - Device Details - Error For Damaged And Broken Screens.")
  public void addValidIMEIAndErrorDevicesDamagesDeviceDetails() {
    String alertDevice = AlertMessages.ALERT_DEVICE_DAMAGE.get();

    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    deviceDetailsPage.fillImeiDeviceDetails(deviceProtectionData.imei());
    deviceDetailsPage.selectNoOptionDamageDeviceDetails();
    deviceDetailsPage.selectYesOptionLessDaysDeviceDetails();
    assertThat(commonPage.getAlertMessage()).isEqualTo(alertDevice);
  }

  @Test(description = "Checkout - Device Details - Invalid IMEI And Answers.")
  public void addInvalidIMEIAndAnswersDeviceDetails() {
    String alertIMEI = AlertMessages.ALERT_INVALID_IMEI.get();
    String alertDevice = AlertMessages.ALERT_DEVICE_MORE_MONTHS.get();

    deviceProtectionPage.confirmYourDevicePriceProtection();
    deviceProtectionPage.selectDevicePriceProtection(deviceProtectionData.deviceProtectionPrice());
    deviceProtectionPage.selectDeviceProtectionButton();
    deviceDetailsPage.fillImeiDeviceDetails(deviceProtectionData.deviceProtectionPrice());
    deviceDetailsPage.selectNoOptionDamageDeviceDetails();
    deviceDetailsPage.selectNoOptionLessDaysDeviceDetails();
    deviceDetailsPage.selectNoOptionLessMonthsDeviceDetails();
    assertThat(commonPage.getInvalidMessage()).isEqualTo(alertIMEI);
    assertThat(commonPage.getAlertMessage()).isEqualTo(alertDevice);
  }
}
