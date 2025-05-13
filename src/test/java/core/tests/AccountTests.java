package core.tests;

import core.pages.account.LogInPage;
import core.pages.account.RegisterPage;
import core.pages.dashboard.DashboardPage;
import core.pages.menu.MainMenuPage;
import core.utils.CredentialsUtils;
import core.utils.TestBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import static core.constants.AppConstants.REGISTER_SUCCESS_MSG;
import static org.testng.Assert.assertTrue;


public class AccountTests extends TestBase {

    @Test
    public void createAccount() {
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToRegister();

        RegisterPage register = new RegisterPage(driver);
        String email = "am" + System.currentTimeMillis() + "@demo.com";
        String password = "Password123";
        register.fillForm("Ale", "AM", "Mersa", email, password, "Password123");
        CredentialsUtils.saveCredential(email, password);

        assertTrue(register.getSuccessMessage().contains(REGISTER_SUCCESS_MSG));
        menu.logout();
    }


    @Test
    public void logIn() {
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToLogin();

        LogInPage loginPage = new LogInPage(driver);
        loginPage.logIn();

        DashboardPage dashboard = new DashboardPage(driver);
        String actualWelcome = dashboard.getWelcomeMessageText();
        System.out.println(actualWelcome);

        String fullName = CredentialsUtils.getCredential("fullName").toUpperCase();
        System.out.println(fullName);
        Assert.assertTrue(actualWelcome.equals("WELCOME, " + fullName+ "!"),
                "Welcome message does not contain expected name: " + fullName);
    }
    }