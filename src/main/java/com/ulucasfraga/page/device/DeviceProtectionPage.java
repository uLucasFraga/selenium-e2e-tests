package com.ulucasfraga.page.device;

import com.ulucasfraga.page.device.common.NavigationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DeviceProtectionPage extends NavigationPage {

  @FindBy(css = "#selected")
  private WebElement selectDevicePrice;

  @FindBy(css = "#list > li.item-selected")
  private WebElement itemSelected;

  @Step
  public void selectDevicePrice(String devicePrice) {
    new Select(this.selectDevicePrice).selectByVisibleText(devicePrice);
  }

  @Step
  public String getDevicePurchaseText() {
    return itemSelected.getText();
  }
}
