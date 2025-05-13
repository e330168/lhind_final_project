package core.pages.account;

import core.elements.account.LogInPageElements;
import core.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    BasePageObject basePageObject = new BasePageObject(DriverProvider.getDriverProvider());
    LogInPageElements logInPageElements =  new LogInPageElements();
    private final WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        LogInPageElements elements = new LogInPageElements();
        PageFactory.initElements(driver, elements);
    }

    public void setField(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "email":
                basePageObject
                        .getWebElementUtils()
                        .sendKeysToElementWithWait(logInPageElements.email, value, 2);
                break;

            case "password":
                basePageObject
                        .getWebElementUtils()
                        .sendKeysToElementWithWait(logInPageElements.password, value, 2);
                break;

            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }


    public void setCheckBox() {
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(logInPageElements.remember_me_chB)
                .click();
    }

    public void clickLogInButton() {
        WebElementUtils.safeClick(driver, logInPageElements.submitButton, 5);
    }


    public void logIn() {
        String email = CredentialsUtils.getCredential("email");
        String password = CredentialsUtils.getCredential("password");
        setField("email",email);
        setField("password",password);
        setCheckBox();
        clickLogInButton();
    }
}
