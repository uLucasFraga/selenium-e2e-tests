package com.ulucasfraga.page.checkout;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.common.UtilsPage;
import io.github.sukgu.support.ElementFieldDecorator;
import io.github.sukgu.support.FindElementBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class CheckoutPage extends UtilsPage {

  public CheckoutPage() {
    ElementFieldDecorator decorator =
        new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getDriver()));
    PageFactory.initElements(decorator, this);
  }

  @FindElementBy(css = "p[class='title']")
  private WebElement productNameCheckoutPage;

  @FindElementBy(css = "#providerName")
  private WebElement providerNameCheckoutPage;

  @FindElementBy(css = "#subscriptionRenewal")
  private WebElement subscriptionRenewalCheckoutPage;

  @FindElementBy(css = "#subscriptionStartDate")
  private WebElement subscriptionStartDateCheckoutPage;

  @Step
  public String getProductNameCheckout() {
    waitElement(this.productNameCheckoutPage);
    return getTextFromLabel(this.productNameCheckoutPage);
  }

  @Step
  public String getProviderNameCheckout() {
    waitElement(this.providerNameCheckoutPage);
    return getTextFromLabel(this.providerNameCheckoutPage);
  }

  @Step
  public String getSubscriptionStartDateCheckout() {
    waitElement(this.subscriptionStartDateCheckoutPage);
    return getTextFromLabel(this.subscriptionStartDateCheckoutPage);
  }

  @Step
  public String getSubscriptionRenewalCheckout() {
    waitElement(this.subscriptionRenewalCheckoutPage);
    return getTextFromLabel(this.subscriptionRenewalCheckoutPage);
  }
}
