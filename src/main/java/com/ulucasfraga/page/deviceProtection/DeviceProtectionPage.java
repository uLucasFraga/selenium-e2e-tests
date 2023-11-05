package com.ulucasfraga.page.deviceProtection;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.common.UtilsPage;
import io.github.sukgu.support.ElementFieldDecorator;
import io.github.sukgu.support.FindElementBy;
import io.qameta.allure.Step;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class DeviceProtectionPage extends UtilsPage {

  public DeviceProtectionPage() {
    ElementFieldDecorator decorator =
        new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getDriver()));
    PageFactory.initElements(decorator, this);
  }

  @FindElementBy(css = "button[id='onetrust-accept-btn-handler']")
  private WebElement acceptCookiesButton;

  @FindElementBy(css = "p[class='h0 banner-title']")
  private WebElement bannerTitleDeviceProtectionPage;

  @FindElementBy(css = "span[id='selected']")
  private WebElement selectDevicePriceOptionProtectionPage;

  @FindElementBy(css = "span[class='edi-counter__counter ascend']")
  private WebElement devicePlanPriceCounterProtectionPage;

  @FindElementBy(css = "span[class='counter-val']")
  private WebElement devicePlanPriceProtectionPage;

  @FindElementBy(xpath = "//span[@class='cta-text'][text()='Select']")
  private WebElement selectButtonDeviceProtectionPage;

  @FindElementBy(css = "#productSummary")
  private WebElement productSummaryDeviceProtectionPage;

  @FindElementBy(css = "p[class='card-title']")
  private WebElement productNameDeviceProtectionPage;

  @Step
  public void acceptCookiesDeviceProtection() {
    waitElement(this.acceptCookiesButton);
    isVisible(this.acceptCookiesButton);
    acceptCookies(this.acceptCookiesButton);
  }

  @Step
  public void confirmYourDevicePriceProtection(String devicePrice) {
    isVisible(this.selectDevicePriceOptionProtectionPage);
    click(this.selectDevicePriceOptionProtectionPage);
    selectDevicePriceProtection(devicePrice);
    isClickable(this.selectButtonDeviceProtectionPage);
  }

  @Step
  public void selectDevicePriceProtection(String devicePrice) {
    isClickable(this.selectButtonDeviceProtectionPage);
    clickElementListByText(devicePrice);
    isClickable(this.selectButtonDeviceProtectionPage);
    isVisible(this.selectButtonDeviceProtectionPage);
  }

  @Step
  public void selectDeviceProtectionButton() {
    waitElement(this.selectButtonDeviceProtectionPage);
    isVisible(this.selectButtonDeviceProtectionPage);
    click(this.selectButtonDeviceProtectionPage);
    isUrlContainsValue("/checkout");
  }

  @Step
  public String getDevicePriceProtection() {
    waitElement(this.selectDevicePriceOptionProtectionPage);
    isVisible(this.selectDevicePriceOptionProtectionPage);
    return getTextFromLabel(this.selectDevicePriceOptionProtectionPage);
  }

  @Step
  public String getDevicePlanPriceProtectionPage() {
    waitElement(this.devicePlanPriceProtectionPage);
    isVisible(this.devicePlanPriceProtectionPage);
    return getTextFromLabel(this.devicePlanPriceProtectionPage);
  }

  @Step
  public String getProductNameDeviceProtection() {
    waitElement(this.productNameDeviceProtectionPage);
    isVisible(this.productNameDeviceProtectionPage);
    return getTextFromLabel(this.productNameDeviceProtectionPage);
  }

  @Step
  public Boolean checkContentAndURLDeviceProtection() {
    waitElement(this.productSummaryDeviceProtectionPage);
    isVisible(this.productSummaryDeviceProtectionPage);
    return isUrlContainsValue("/device-protection/checkout/payment");
  }

  @Step
  public String getUtmSourceDeviceProtection() {
    String actualUrl = getUrl();

    Pattern pattern = Pattern.compile("[?&]utm_source=ascend&h=([^&]+)");
    Matcher matcher = pattern.matcher(actualUrl);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }
}
