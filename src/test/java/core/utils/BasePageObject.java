package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePageObject {
    private ElementLocatorFactory rootFactory;
    private final Duration defaultDuration = Duration.ofMillis(2);
    private DriverProvider driverP;
    private WaitUtils waitUtils;
    private WebElementUtils webElementUtils;
    protected JavascriptExecutor js;
    protected WebDriverWait wait;

    private WebDriver driver = DriverProvider.getDriver();

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }


    public BasePageObject(DriverProvider driverP) {
        this.driverP = driverP;
        rootFactory = new DefaultElementLocatorFactory(driver);
        PageFactory.initElements(rootFactory, this);
        this.js = (JavascriptExecutor) driver;
    }

    public WaitUtils getWaitUtils() {
        if (waitUtils == null) {
            waitUtils = new WaitUtils(getDriver(), defaultDuration);
        }
        return waitUtils;
    }

    public WebElementUtils getWebElementUtils() {
        if (webElementUtils == null) {
            webElementUtils = new WebElementUtils(getDriver(), defaultDuration);
        }
        return webElementUtils;
    }

    public DriverProvider getDriver() {
        return driverP;
    }

    public void scrollTo(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForPageToLoad() {
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
    }

    public BasePageObject(DriverProvider driverP, By locator) {
        this.driverP = driverP;
        try{
            getWaitUtils().waitForElementVisible(locator);
        } catch (Exception ex) {

        }
        rootFactory = new DefaultElementLocatorFactory(
                driver.findElement(locator));
        PageFactory.initElements(rootFactory, this);
    }

    public BasePageObject(DriverProvider driver, WebElement rootElement) {
        this.driverP = driver;
        rootFactory = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(rootFactory, this);
    }

    public void getUrl(String url){
        driver.get(url);
    }

    public static class PageUtils {

        public static boolean isUrlContains(WebDriver driver, String partialUrl, int timeoutSeconds) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                        .until(ExpectedConditions.urlContains(partialUrl));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static boolean isTitleContains(WebDriver driver, String partialTitle, int timeoutSeconds) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                        .until(ExpectedConditions.titleContains(partialTitle));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static String getCurrentUrl(WebDriver driver) {
            return driver.getCurrentUrl();
        }

        public static String getPageTitle(WebDriver driver) {
            return driver.getTitle();
        }

        public static void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
                    webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
        }

        public static boolean isElementPresent(WebDriver driver, By locator) {
            try {
                driver.findElement(locator);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        public static boolean isElementVisible(WebDriver driver, By locator, int timeoutSeconds) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        }
    }
}
