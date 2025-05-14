package core.utils;

import core.constants.AppConstants;
import core.pages.account.LogInPage;
import core.pages.cookies.CookieConsentPage;
import core.pages.menu.MainMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverProvider.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(AppConstants.BASE_URL);
        CookieConsentPage cookiePage = new CookieConsentPage();
        cookiePage.acceptCookies(wait);
    }

    public void logIn() {
        driver.get(AppConstants.BASE_URL);
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToLogin();
        LogInPage loginPage = new LogInPage(driver, wait);
        loginPage.logIn();
    }

    @AfterMethod
    public void tearDown() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
    }
}