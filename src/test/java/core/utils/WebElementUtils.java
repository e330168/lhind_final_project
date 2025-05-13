package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class WebElementUtils {

        public WebElementUtils(DriverProvider driver, Duration defaultDuration) {
        }

        public void sendKeysToElementWithWait(WebElement element, String value, long millsWait) {
        element.sendKeys(value);
        WaitUtils.waitFor(millsWait);
    }

    public static void safeClick(WebDriver driver, WebElement element, int timeoutSeconds) {
        try {
            waitForClickable(driver, element, timeoutSeconds).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            jsClick(driver, element);
        }
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static WebElement waitForClickable(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
