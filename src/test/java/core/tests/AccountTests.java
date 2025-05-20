package core.tests;

import core.pages.account.LogInPage;
import core.pages.account.RegisterPage;
import core.pages.dashboard.ProductsGridPage;
import core.pages.menu.MainMenuPage;
import core.utils.CredentialsUtils;
import core.utils.ScreenshotUtils.ScreenshotListener;
import core.utils.SkipLogIn;
import core.utils.TestBase;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
public class AccountTests extends TestBase {

    @SkipLogIn
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

            ProductsGridPage ProductsGridPage=new ProductsGridPage(driver, wait);
            String successM=ProductsGridPage.getSuccessRegisterMessage();
            String regMessage="Thank you for registering with Tealium Ecommerce.";

            Assert.assertTrue(successM.equals(regMessage),"Not registered successfully.");
            menu.logout();
        }
    }

    @SkipLogIn
    @Test
    public void logIn() {
        MainMenuPage menu = new MainMenuPage(driver, wait);
        menu.goToLogin();

        LogInPage loginPage = new LogInPage(driver,wait);
        loginPage.logIn();

        ProductsGridPage dashboard = new ProductsGridPage(driver,wait);
        String actualWelcome = dashboard.getWelcomeMessageText();
        System.out.println(actualWelcome);

        String fullName = CredentialsUtils.getCredential("fullName").toUpperCase();
        System.out.println(fullName);
        Assert.assertTrue(actualWelcome.equals("WELCOME, " + fullName+ "!"),
                "Welcome message does not contain expected name: " + fullName);
        }
    }