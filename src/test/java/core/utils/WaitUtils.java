package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private Duration defaultDuration;
    private WebDriver driver = DriverProvider.getDriver();
    private static final int TIMEOUT = 5;

    public WaitUtils(Duration defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    public static void waitFor(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e1) {
            System.out.println("ERROR in waitForMethod");
        }
    }

    public static WebElement waitForVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementVisibleWithCustomTime(long mills, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisibleWithCustomTime(long mills, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementclicableWithCustomTime(long mills, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(mills));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return waitForElementclicableWithCustomTime(defaultDuration.toMillis(), element);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisibleWithCustomTime(defaultDuration.toMillis(), element);
    }

}