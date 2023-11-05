package com.ulucasfraga.report;

import static com.ulucasfraga.config.ConfigurationManager.configuration;
import static org.openqa.selenium.OutputType.BYTES;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.enums.Target;
import io.qameta.allure.Attachment;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.TakesScreenshot;

public class AllureManager {

  private AllureManager() {}

  public static void setAllureEnvironmentInformation() {
    var basicInfo =
        new HashMap<>(
            Map.of(
                "Test URL", configuration().url(),
                "Target execution", configuration().target(),
                "Global timeout", String.valueOf(configuration().timeout()),
                "Headless mode", String.valueOf(configuration().headless()),
                "Faker locale", configuration().faker(),
                "Local browser", configuration().browser()));

    if (configuration().target().equals(Target.SELENIUM_GRID.name())) {
      var gridMap =
          Map.of("Grid URL", configuration().gridUrl(), "Grid port", configuration().gridPort());
      basicInfo.putAll(gridMap);
    }

    AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.copyOf(basicInfo));
  }

  @Attachment(value = "Failed test screenshot", type = "image/png")
  public static void takeScreenshotToAttachOnAllureReport() {
    ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
  }

  @Attachment(value = "Browser information", type = "text/plain")
  public static void addBrowserInformationOnAllureReport() {
    DriverManager.getInfo();
  }
}
