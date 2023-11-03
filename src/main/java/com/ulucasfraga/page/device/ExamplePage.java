package com.ulucasfraga.page.device;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.device.common.NavigationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ExamplePage extends NavigationPage {

  @Step
  public void selectExample(String example) {
    DriverManager.getDriver().findElement(By.xpath("//h6[text()='" + example + "']")).click();
  }
}
