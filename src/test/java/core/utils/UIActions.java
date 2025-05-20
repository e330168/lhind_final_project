package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UIActions {

    public static void click(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementNotInteractableException e) {
            clickUsingJS(driver, element);
        }
    }

    public static void clickUsingJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void hoverOver(WebElement element) {
        WebDriver driver = DriverProvider.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
