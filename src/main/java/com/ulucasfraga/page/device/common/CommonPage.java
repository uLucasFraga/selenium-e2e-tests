package com.ulucasfraga.page.device.common;

import com.ulucasfraga.driver.DriverManager;
import com.ulucasfraga.page.device.AbstractPageObject;
import io.github.sukgu.Shadow;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage extends AbstractPageObject {

  private static final int DEFAULT_TIME_WAIT = 10;
  Shadow shadow = new Shadow(DriverManager.getDriver());

  protected String getUrl() {
    return DriverManager.getDriver().getCurrentUrl();
  }

  protected Boolean isUrlContainsValue(String text) {
    return getUrl().contains(text);
  }

  protected void acceptCookies(WebElement element) {
    isClickable(element);
    click(element);
  }

  protected WebElement waitElement(WebElement element) {
    return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.visibilityOf(element));
  }

  protected List<WebElement> waitElements(By element) {
    return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
  }

  protected void isVisible(WebElement element) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.visibilityOf(element));
    element.isDisplayed();
  }

  protected void isVisibleBy(By locator) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected void isNotVisible(WebElement element) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.invisibilityOf(element));
  }

  protected void isClickable(WebElement element) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.elementToBeClickable(element));
    if (element.isDisplayed()) {
      element.isEnabled();
    }
  }

  protected void waitForTextInElement(WebElement element, String text) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.textToBePresentInElementValue(element, text));
  }

  protected void waitForTextPresentInElement(WebElement element, String textToBeWait) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.textToBePresentInElement(element, textToBeWait));
  }

  protected void waitForElementToBeSelected(WebElement element) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.elementToBeSelected(element));
  }

  protected void waitElementInvisible(WebElement element) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIME_WAIT))
        .until(ExpectedConditions.invisibilityOf(element));
  }

  protected void waitForChecked(WebElement element) {
    element.isSelected();
  }

  protected void waitForNotChecked(WebElement element) {
    element.isSelected();
  }

  protected void isVisibleOnMouse(WebElement element) {
    isVisible(element);
    new Actions(DriverManager.getDriver()).moveToElement(element).perform();
    element.isDisplayed();
  }

  protected void isElementAttachedToHtml(WebElement element) {
    try {
      waitElement(element);
    } catch (Exception ignored) {
    }
  }

  protected WebElement getElement(By locator) {
    WebElement element = DriverManager.getDriver().findElement(locator);
    return waitElement(element);
  }

  protected List<WebElement> getElements(By element) {
    return waitElements(element);
  }

  protected void fillInput(WebElement element, String datatype) {
    isVisible(element);
    element.clear();
    this.waitForTextInElement(element, "");
    element.sendKeys(datatype);
  }

  protected void clickOnMouse(WebElement element) {
    isVisible(element);
    new Actions(DriverManager.getDriver()).moveToElement(element).perform();
    element.click();
  }

  protected void clickElementListByText(String text) {
    WebElement element =
        shadow.findElementByXPath("//ul[@id='list']/li[contains(text(), '" + text + "')]");
    isClickable(element);
    if (element.getText().equalsIgnoreCase(text)) {
      element.click();
    }
  }

  protected void clickElementByTextList(List<WebElement> elements, String text) {
    for (WebElement element : elements) {
      if (text.equals(getTextFromLabel(element))) {
        isClickable(element);
        element.click();
        break;
      }
    }
  }

  protected void click(WebElement element) {
    isClickable(element);
    element.click();
  }

  protected void check(WebElement element) {
    if (!element.isSelected()) {
      click(element);
      waitForChecked(element);
    }
  }

  protected void unCheck(WebElement element) {
    if (element.isSelected()) {
      click(element);
      waitForNotChecked(element);
    }
  }

  protected String getTextFromLabel(WebElement element) {
    return element.getText().trim();
  }

  protected String getAttributeFromLabel(WebElement element, String value) {
    return element.getAttribute(value);
  }

  protected String getTextOfVisibleElement(WebElement element) {
    String text = null;
    try {
      text = element.getText().trim();
    } catch (Exception e) {
      System.out.println(element + " : " + e);
    }
    return text;
  }
}
