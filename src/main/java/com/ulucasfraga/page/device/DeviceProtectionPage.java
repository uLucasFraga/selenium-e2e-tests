package com.ulucasfraga.page.device;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.device.common.CommonPage;
import io.github.sukgu.support.ElementFieldDecorator;
import io.github.sukgu.support.FindElementBy;
import io.qameta.allure.Step;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class DeviceProtectionPage extends CommonPage {

  public DeviceProtectionPage() {
    ElementFieldDecorator decorator =
        new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getDriver()));
    PageFactory.initElements(decorator, this);
  }

  @FindElementBy(css = "button[id='onetrust-accept-btn-handler']")
  private WebElement acceptCookiesButton;

  @FindElementBy(css = "span[id='selected']")
  private WebElement selectDevicePriceOption;

  @FindElementBy(css = "span[class='counter-val']")
  private WebElement valuePlanDevicePrice;

  @FindElementBy(xpath = "//span[@class='cta-text'][text()='Select']")
  private WebElement selectPaymentButton;

  @FindElementBy(css = "p[class='final-price']")
  private WebElement finalPrice;

  @FindElementBy(css = "#productSummary")
  private WebElement productSummaryScreen;

  @Step
  public void confirmYourDevicePrice() {
    acceptCookies(this.acceptCookiesButton);
    click(this.selectDevicePriceOption);
  }

  @Step
  public void selectDevicePrice(String devicePrice) {
    clickElementListByText(devicePrice);
  }

  @Step
  public void selectPaymentSummaryButton() {
    click(this.selectPaymentButton);
  }

  @Step
  public String getDevicePrice() {
    waitElement(this.selectDevicePriceOption);
    return getTextFromLabel(this.selectDevicePriceOption);
  }

  @Step
  public String getValuePlanDevicePrice() {
    waitElement(this.valuePlanDevicePrice);
    return getTextFromLabel(this.valuePlanDevicePrice);
  }

  @Step
  public String getValueFinalPrice() {
    waitElement(this.finalPrice);
    return getTextFromLabel(this.finalPrice);
  }

  @Step
  public Boolean checkContentAndURL() {
    waitElement(this.productSummaryScreen);
    return isUrlContainsValue("/device-protection/checkout/payment");
  }

  @Step
  public String getUtmSource() {
    String actualUrl = getUrl();

    Pattern pattern = Pattern.compile("[?&]utm_source=ascend&h=([^&]+)");
    Matcher matcher = pattern.matcher(actualUrl);
    if (matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }
}
