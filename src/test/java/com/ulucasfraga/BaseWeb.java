package com.ulucasfraga;

import static com.ulucasfraga.config.ConfigurationManager.configuration;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.driver.TargetFactory;
import com.ulucasfraga.report.AllureManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public abstract class BaseWeb {

  @BeforeSuite
  public void beforeSuite() {
    System.setProperty("webdriver.http.factory", "jdk-http-client");
    AllureManager.setAllureEnvironmentInformation();
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters("browser")
  public void preCondition(@Optional("chrome") String browser) {
    WebDriver driver = new TargetFactory().createInstance(browser);
    DriverManager.setDriver(driver);

    DriverManager.getDriver()
        .get(configuration().url() + "/en/ascend/device-protection?utm_source=ascend");
  }

  @AfterMethod(alwaysRun = true)
  public void postCondition() {
    DriverManager.quit();
  }
}
