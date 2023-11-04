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
  public void confirmYourDevicePriceProtection() {
    acceptCookies(this.acceptCookiesButton);
    click(this.selectDevicePriceOptionProtectionPage);
    isClickable(this.selectButtonDeviceProtectionPage);
  }

  @Step
  public void selectDevicePriceProtection(String devicePrice) {
    clickElementListByText(devicePrice);
    isClickable(this.selectButtonDeviceProtectionPage);
  }

  @Step
  public void selectDeviceProtectionButton() {
    isElementAttachedToHtml(this.selectButtonDeviceProtectionPage);
    click(this.selectButtonDeviceProtectionPage);
  }

  @Step
  public String getDevicePriceProtection() {
    isVisible(this.devicePlanPriceCounterProtectionPage);
    waitElement(this.selectDevicePriceOptionProtectionPage);
    return getTextFromLabel(this.selectDevicePriceOptionProtectionPage);
  }

  @Step
  public String getDevicePlanPriceProtectionPage() {
    isVisible(this.devicePlanPriceCounterProtectionPage);
    waitElement(this.devicePlanPriceProtectionPage);
    return getTextFromLabel(this.devicePlanPriceProtectionPage);
  }

  @Step
  public String getProductNameDeviceProtection() {
    isVisible(this.devicePlanPriceCounterProtectionPage);
    waitElement(this.productNameDeviceProtectionPage);
    return getTextFromLabel(this.productNameDeviceProtectionPage);
  }

  @Step
  public Boolean checkContentAndURLDeviceProtection() {
    waitElement(this.productSummaryDeviceProtectionPage);
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
