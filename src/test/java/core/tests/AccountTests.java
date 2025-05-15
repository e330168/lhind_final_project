package core.tests;

import core.elements.dashboard.DashboardPageElements;
import core.pages.account.LogInPage;
import core.pages.account.RegisterPage;
import core.pages.dashboard.DashboardPage;
import core.pages.menu.MainMenuPage;
import core.utils.CredentialsUtils;
import core.utils.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class AccountTests extends TestBase {

    @Test
    public void createAccount() {
        if (!CredentialsUtils.hasCredentials()) {
            MainMenuPage menu = new MainMenuPage(driver, wait);
            menu.goToRegister();

            RegisterPage register = new RegisterPage(driver);
            String email = "am" + System.currentTimeMillis() + "@gmail.com";
  //        String email = "am@gmail.com";
            String password = "1234567";
            register.fillForm("Ale", "AM", "Mersa", email, password, password);
            CredentialsUtils.saveCredential(email, password);

            driver.getCurrentUrl();
            Assert.assertTrue(driver.getCurrentUrl().contains("create"));

            DashboardPage dashboardPage=new DashboardPage(driver, wait);
            String successM=dashboardPage.getSuccessRegisterMessage();
            assertTrue(register.getSuccessMessage().contains(successM));
            menu.logout();
        }
    }


    @Test
    public void logIn() {
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToLogin();

        LogInPage loginPage = new LogInPage(driver,wait);
        loginPage.logIn();

        DashboardPage dashboard = new DashboardPage(driver,wait);
        String actualWelcome = dashboard.getWelcomeMessageText();
        System.out.println(actualWelcome);

        String fullName = CredentialsUtils.getCredential("fullName").toUpperCase();
        System.out.println(fullName);
        Assert.assertTrue(actualWelcome.equals("WELCOME, " + fullName+ "!"),
                "Welcome message does not contain expected name: " + fullName);
        }
    }