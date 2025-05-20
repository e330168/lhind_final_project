package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

}
