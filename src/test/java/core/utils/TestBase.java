package core.utils;

import core.constants.AppConstants;
import core.pages.cookies.CookieConsentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Set;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Set<String> baseUrlUsage = Set.of("createAccount", "logIn");

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverProvider.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (baseUrlUsage.contains(method.getName())) {
            driver.get(AppConstants.BASE_URL);
        }

        CookieConsentPage cookiePage = new CookieConsentPage();
        cookiePage.acceptCookies(wait);
    }


    @AfterMethod
    public void tearDown() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
    }
}