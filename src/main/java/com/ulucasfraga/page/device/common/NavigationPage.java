package com.ulucasfraga.page.device.common;

import com.ulucasfraga.page.device.AbstractPageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationPage extends AbstractPageObject {

  @FindBy(name = "next")
  private WebElement next;

  @FindBy(name = "previous")
  private WebElement previous;

  @FindBy(name = "finish")
  private WebElement finish;

  @Step
  public void next() {
    next.click();
  }

  @Step
  public void previous() {
    previous.click();
  }

  @Step
  public void finish() {
    finish.click();
  }
}
