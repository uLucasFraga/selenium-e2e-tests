package com.ulucasfraga.page;

import static com.ulucasfraga.config.ConfigurationManager.configuration;
import static org.openqa.selenium.support.PageFactory.initElements;

import com.ulucasfraga.driver.DriverManager;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AbstractPageObject {

  protected AbstractPageObject() {
    initElements(
        new AjaxElementLocatorFactory(DriverManager.getDriver(), configuration().timeout()), this);
  }
}
