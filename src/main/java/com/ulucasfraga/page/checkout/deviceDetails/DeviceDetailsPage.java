package com.ulucasfraga.page.checkout.deviceDetails;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.common.UtilsPage;
import io.github.sukgu.support.ElementFieldDecorator;
import io.github.sukgu.support.FindElementBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class DeviceDetailsPage extends UtilsPage {

  public DeviceDetailsPage() {
    ElementFieldDecorator decorator =
        new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getDriver()));
    PageFactory.initElements(decorator, this);
  }

  @FindElementBy(css = "input[id='imei']")
  private WebElement inputImeiDetailsPage;

  @FindElementBy(xpath = "//*[@id='noDamage']//button[@id='button-Y']")
  private WebElement yesDamageOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='noDamage']//button[@id='button-N']")
  private WebElement noDamageOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='lessThan14Days']//button[@id='button-Y']")
  private WebElement yesLessDaysOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='lessThan14Days']//button[@id='button-N']")
  private WebElement noLessDaysOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='lessThan18Months']//button[@id='button-Y']")
  private WebElement yesLessMonthsOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='lessThan18Months']//button[@id='button-N']")
  private WebElement noLessMonthsOptionDetailsPage;

  @FindElementBy(xpath = "//*[@id='contact-details']//iron-icon[@icon='icons:done']")
  private WebElement iconDoneDetailsPage;

  @Step
  public void fillImeiDeviceDetails(String text) {
    waitElement(this.inputImeiDetailsPage);
    fillInput(this.inputImeiDetailsPage, text);
  }

  @Step
  public void selectYesOptionDamageDeviceDetails() {
    waitElement(this.yesDamageOptionDetailsPage);
    click(this.yesDamageOptionDetailsPage);
  }

  @Step
  public void selectNoOptionDamageDeviceDetails() {
    waitElement(this.noDamageOptionDetailsPage);
    click(this.noDamageOptionDetailsPage);
  }

  @Step
  public void selectYesOptionLessDaysDeviceDetails() {
    waitElement(this.yesLessDaysOptionDetailsPage);
    click(this.yesLessDaysOptionDetailsPage);
  }

  @Step
  public void selectNoOptionLessDaysDeviceDetails() {
    waitElement(this.noLessDaysOptionDetailsPage);
    click(this.noLessDaysOptionDetailsPage);
  }

  @Step
  public void selectYesOptionLessMonthsDeviceDetails() {
    waitElement(this.yesLessMonthsOptionDetailsPage);
    click(this.yesLessMonthsOptionDetailsPage);
  }

  @Step
  public void selectNoOptionLessMonthsDeviceDetails() {
    waitElement(this.noLessMonthsOptionDetailsPage);
    click(this.noLessMonthsOptionDetailsPage);
  }

  @Step
  public Boolean checkIconDoneDetails() {
    waitElement(this.iconDoneDetailsPage);
    return isVisible(this.iconDoneDetailsPage);
  }
}
