package core.utils;

import core.constants.AppConstants;
import core.pages.account.LogInPage;
import core.pages.account.cookies.CookieConsentPage;
import core.pages.menu.MainMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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

        Test testAnnotation = method.getAnnotation(Test.class);
        if (testAnnotation != null) {
            String[] depends = testAnnotation.dependsOnMethods();
            if (depends.length == 0) {
                logIn();
            }
        }
    }


    public void logIn() {
        driver.get(AppConstants.BASE_URL);
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToLogin();
        LogInPage loginPage = new LogInPage(driver, wait);
        loginPage.logIn();
    }

//    @AfterMethod
//    public void tearDown(ITestResult result, ITestContext context) {
//        String currentMethod = result.getMethod().getMethodName();
//
//        boolean hasDependentScheduled = Arrays.stream(context.getAllTestMethods())
//                .anyMatch(m -> Arrays.asList(m.getMethodsDependedUpon()).contains(currentMethod));
//
//        if (!hasDependentScheduled && driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if ((result.getMethod().getMethodName().equals("checkSortedByPriceAnd2WishListSelected") ||
                (result.getMethod().getMethodName().equals("shoppingCart"))) &&
                result.getStatus() == ITestResult.SUCCESS) {
            return;
        }
        if (driver != null) {
                driver.quit();
                driver = null;
            }
    }
}