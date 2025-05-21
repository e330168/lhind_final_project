package core.utils;

import core.constants.AppConstants;
import core.pages.account.LogInPage;
import core.pages.account.cookies.CookieConsentPage;
import core.pages.dashboard.MenPage;
import core.pages.dashboard.WomenPage;
import core.pages.navigation.MainMenuPage;
import core.pages.navigation.NavBarMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected WomenPage womenPage;
    protected NavBarMenuPage navBar;
    protected MainMenuPage mainMenuPage;
    protected MenPage menPage;

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverProvider.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(AppConstants.BASE_URL);

        navBar = new NavBarMenuPage(driver, wait);
        mainMenuPage = new MainMenuPage(driver, wait);
        womenPage = new WomenPage(driver, wait);
        menPage = new MenPage(driver, wait);

        CookieConsentPage cookiePage = new CookieConsentPage();
        cookiePage.acceptCookies(wait);

        Test testAnnotation = method.getAnnotation(Test.class);
        if (testAnnotation != null) {
            String[] depends = testAnnotation.dependsOnMethods();
            if (!method.isAnnotationPresent(SkipLogIn.class) && depends.length == 0) {
                logIn();
            }
        }
    }

    public void logIn() {
        driver.get(AppConstants.BASE_URL);
        mainMenuPage.goToLogin();
        LogInPage loginPage = new LogInPage(driver, wait);
        loginPage.logIn();
    }


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