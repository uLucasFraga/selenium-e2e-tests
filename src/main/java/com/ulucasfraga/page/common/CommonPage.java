package com.ulucasfraga.page.common;

import com.ulucasfraga.driver.DriverManager;
import io.github.sukgu.support.ElementFieldDecorator;
import io.github.sukgu.support.FindElementBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class CommonPage extends UtilsPage {

  public CommonPage() {
    ElementFieldDecorator decorator =
        new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getDriver()));
    PageFactory.initElements(decorator, this);
  }

  @FindElementBy(css = "p[class='final-price']")
  private WebElement finalPrice;

  @FindElementBy(css = "span[class='form-control-feedback invalid show']")
  private WebElement invalidAlertMessage;

  @FindElementBy(css = "p[class='unable-proceed-message']")
  private WebElement unableAlertMessage;

  @Step
  public String getFinalPrice() {
    waitForElementToBeVisible(this.finalPrice);
    return getTextFromLabel(this.finalPrice);
  }

  @Step
  public String getInvalidMessage() {
    waitForElementToBeVisible(this.invalidAlertMessage);
    return getTextFromLabel(this.invalidAlertMessage);
  }

  @Step
  public String getAlertMessage() {
    waitForElementToBeVisible(unableAlertMessage);
    isVisible(this.unableAlertMessage);
    return getTextFromLabel(this.unableAlertMessage);
  }
}
