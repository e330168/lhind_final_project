package core.pages.account;

import core.elements.account.LogInElements;
import core.utils.BasePageObject;
import core.utils.CredentialsUtils;
import core.utils.DriverProvider;
import core.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {
    BasePageObject basePageObject = new BasePageObject(DriverProvider.getDriverProvider());
    LogInElements logInPageElements;
    private final WebDriver driver;
    protected final WebDriverWait wait;

    public LogInPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.logInPageElements = new LogInElements();
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
