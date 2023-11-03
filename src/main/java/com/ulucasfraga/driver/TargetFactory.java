package com.ulucasfraga.driver;

import com.ulucasfraga.enums.Target;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.ulucasfraga.config.ConfigurationManager.configuration;
import static com.ulucasfraga.driver.BrowserFactory.valueOf;
import static java.lang.String.format;

public class TargetFactory {

  private static final Logger logger = LogManager.getLogger(TargetFactory.class);

  public WebDriver createInstance(String browser) {
    Target target = Target.get(configuration().target().toUpperCase());

    return switch (target) {
      case LOCAL -> valueOf(configuration().browser().toUpperCase()).createLocalDriver();
      case LOCAL_SUITE -> valueOf(browser.toUpperCase()).createLocalDriver();
      case SELENIUM_GRID -> createRemoteInstance(valueOf(browser.toUpperCase()).getOptions());
      case TESTCONTAINERS -> valueOf(configuration().browser().toUpperCase())
          .createTestContainerDriver();
    };
  }

  private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
    RemoteWebDriver remoteWebDriver = null;
    try {
      String gridURL =
          format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());

      remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
    } catch (java.net.MalformedURLException e) {
      logger.error("Grid URL is invalid or Grid is not available");
      logger.error(format("Browser: %s", capability.getBrowserName()), e);
    } catch (IllegalArgumentException e) {
      logger.error(format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
    }

    return remoteWebDriver;
  }
}
