package com.selenium.elementHelper.web;

import com.selenium.elementHelper.WaitHelper;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumHelper {

  private final WaitHelper waitHelper;
  private final JavaScriptHelper jsHelper;
  private final WebDriver driver;
  private static final boolean IS_DEBUG =
      Boolean.parseBoolean(Dotenv.configure().ignoreIfMissing().load().get("IS_DEBUG"));

  public SeleniumHelper(WebDriver driver) {
    this.driver = driver;
    waitHelper = new WaitHelper(driver);
    jsHelper = new JavaScriptHelper(driver);
  }

  // wait till element become enable
  private WebElement waitTillElementIsEnable(WebElement element) throws InterruptedException {
    var numberOfTries = 1;
    while (!isElementEnable(element) && numberOfTries != 15) {
      waitHelper.hardWait(numberOfTries);
      numberOfTries++;
    }
    if (!isElementEnable(element)) throw new RuntimeException("Element is not in enable state");
    return element;
  }

  private void clickOnElement(WebElement element) {
    var tryToClickOnElement = 10;
    boolean staleElement;

    do {
      try {
        element.click();
        staleElement = false;
      } catch (Exception e) {
        staleElement = true;
      }
      tryToClickOnElement--;
    } while (tryToClickOnElement != 0 && staleElement);
  }

  // First clear the value from element and enter value in input box
  private void enterText(WebElement element, String value) throws InterruptedException {
    var enableElement = waitTillElementIsEnable(element);
    enableElement.clear();
    enableElement.sendKeys(value);
  }

  // Return that element is in enable state or disable state
  private boolean isElementEnable(WebElement element) {
    return element.isEnabled();
  }

  // Return that element is in disable state or enable state
  public boolean isElementEnabled(By by) {
    var elementIsEnable = waitHelper.waitForElementToBeVisible(by);
    return isElementEnable(elementIsEnable);
  }

  public boolean isElementEnabled(By by, int second) {
    var elementIsEnable = waitHelper.waitForElementToBeVisible(by, second);
    return isElementEnable(elementIsEnable);
  }

  // Use this for sendKey to an element
  public void scrollAndEnterText(By by, String value) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    jsHelper.scrollToElementIfNotInView(element);
    enterText(element, value);
  }

  public void scrollAndEnterText(By by, String value, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    jsHelper.scrollToElementIfNotInView(element);
    enterText(element, value);
  }

  public void enterText(By by, String value) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    enterText(element, value);
  }

  public void enterText(By by, String value, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    enterText(element, value);
  }

  // This method used to click on element
  public void scrollAndClickOn(By by) throws InterruptedException {
    var element = waitHelper.waitForElementToBeClickable(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    var elementIsEnable = waitTillElementIsEnable(element);
    jsHelper.scrollToElementIfNotInView(elementIsEnable);
    clickOnElement(elementIsEnable);
  }

  public void scrollAndClickOn(By by, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeClickable(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    var elementIsEnable = waitTillElementIsEnable(element);
    jsHelper.scrollToElementIfNotInView(elementIsEnable);
    clickOnElement(elementIsEnable);
  }

  public void ClickOn(By by) throws InterruptedException {
    var element = waitHelper.waitForElementToBeClickable(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    var elementIsEnable = waitTillElementIsEnable(element);
    clickOnElement(elementIsEnable);
  }

  public void ClickOn(By by, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeClickable(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    var elementIsEnable = waitTillElementIsEnable(element);
    clickOnElement(elementIsEnable);
  }

  public boolean isElementDisplayed(By by) {
    WebElement element = null;
    try {
      element = waitHelper.waitForElementToBeVisible(by);
      if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    } catch (Exception _) {
    }
    return element != null;
  }

  public boolean isElementDisplayed(By by, int second) {
    WebElement element = null;
    try {
      element = waitHelper.waitForElementToBeVisible(by, second);
      if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    } catch (Exception _) {
    }
    return element != null;
  }

  public void selectOptionByText(By by, String text) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByValue(text);
  }

  public void selectOptionByText(By by, String text, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByValue(text);
  }

  public void selectOptionByValue(By by, String value) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByValue(value);
  }

  public void selectOptionByValue(By by, String value, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByValue(value);
  }

  public void selectOptionByIndex(By by, int index) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByIndex(index);
  }

  public void selectOptionByValue(By by, int index, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    new Select(element).selectByIndex(index);
  }

  public List<String> getAllOptionText(By by) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    return new Select(element).getOptions().stream().map(WebElement::getText).toList();
  }

  public List<String> getAllOptionText(By by, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    return new Select(element).getOptions().stream().map(WebElement::getText).toList();
  }

  public void switchToIframe(By by) {
    waitHelper.waitForFrameToBeAvailableAndSwitchToIt(by);
  }

  public void switchToIframe(By by, int second) {
    waitHelper.waitForFrameToBeAvailableAndSwitchToIt(by, second);
  }

  public boolean isRadioButtonSelected(By by) {
    return waitHelper.waitForElementToBeVisible(by).isSelected();
  }

  public boolean isRadioButtonSelected(By by, int second) {
    return waitHelper.waitForElementToBeVisible(by, second).isSelected();
  }

  public boolean isCheckBoxButtonSelected(By by) {
    return waitHelper.waitForElementToBeVisible(by).isSelected();
  }

  public boolean isCheckBoxButtonSelected(By by, int second) {
    return waitHelper.waitForElementToBeVisible(by, second).isSelected();
  }

  public void clickOnElementUsingJavaScript(By by) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    jsHelper.javaScriptClickOn(element);
  }

  public void clickOnElementUsingJavaScript(By by, int second) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    jsHelper.javaScriptClickOn(element);
  }

  public void enterTextUsingJavaScript(By by, String value) {
    var element = waitHelper.waitForElementToBeVisible(by);
    jsHelper.javaScriptEnterText(element, value);
  }

  public void enterTextUsingJavaScript(By by, String value, int second) {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    jsHelper.javaScriptEnterText(element, value);
  }

  public void scrollToTheElement(By by) {
    var element = waitHelper.waitForElementToBeVisible(by);
    jsHelper.scrollToElementCenter(element);
  }

  public void scrollToTheElement(By by, int second) {
    var element = waitHelper.waitForElementToBeVisible(by, second);
    jsHelper.scrollToElementCenter(element);
  }

  public String getText(By by) throws InterruptedException {
    var element = waitHelper.waitForElementToBeVisible(by);
    if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
    return element.getText().trim();
  }

  public List<String> getAllElementsText(By by) {
    var elements = waitHelper.waitForAllElementToBeVisible(by);
    if (IS_DEBUG)
      try {
        for (WebElement element : elements) jsHelper.javaScriptHighlightElement(element);
      } catch (Exception _) {
      }
    return elements.stream().map(x -> x.getText().trim().replace("\n", "")).toList();
  }

  public void waitTillPageLoadedProperly() {
    waitHelper.waitForPageContentLoaded();
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  public boolean isElementSelected(By by) {
    return waitHelper.waitForElementToBeVisible(by).isSelected();
  }

  public boolean isElementSelected(By by, int second) {
    return waitHelper.waitForElementToBeVisible(by, second).isSelected();
  }

  public boolean isElementHaveGivenClass(By by, String cssValue) {
    var element = waitHelper.waitForElementToBeVisible(by);
    return element.getAttribute("class").contains(cssValue);
  }
}
