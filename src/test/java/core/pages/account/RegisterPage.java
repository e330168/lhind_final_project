package core.pages.account;

import core.elements.account.RegisterElements;
import core.utils.BasePageObject;
import core.utils.CredentialsUtils;
import core.utils.DriverProvider;
import core.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends RegisterElements {
    BasePageObject basePageObject = new BasePageObject(DriverProvider.getDriver());
    RegisterElements RegisterElements =  new RegisterElements();
    WebDriver driver=DriverProvider.getDriver();

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterButton(){
        basePageObject
                .getWaitUtils()
                .waitForElementClickable(registerButton)
                .click();
    }

    public void setField(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "firstname":
                basePageObject
                        .getWaitUtils()
                        .waitForElementVisible(RegisterElements.firstName)
                        .sendKeys(value);
                break;

            case "middlename":
                basePageObject
                        .getWaitUtils()
                        .waitForElementVisible(RegisterElements.middleName)
                        .sendKeys(value);
                break;

            case "lastname":
                basePageObject
                        .getWebElementUtils()
                        .sendKeysToElementWithWait(RegisterElements.lastName, value, 2);
                break;

            case "email":
                basePageObject
                        .getWaitUtils()
                        .waitForElementVisible(RegisterElements.emailAddress)
                        .sendKeys(value);
                break;

            case "password":
                basePageObject
                        .getWebElementUtils()
                        .sendKeysToElementWithWait(RegisterElements.password, value, 2);
                break;

            case "confirmpassword":
                basePageObject
                        .getWebElementUtils()
                        .sendKeysToElementWithWait(RegisterElements.confirmP, value, 2);
                break;

            default:
                throw new IllegalArgumentException("Unknown field name: " + fieldName);
        }
    }

    public void checkboxRM() {
        if (!WebElementUtils.isElementDisplayed(RegisterElements.checkbox)) return;
        try {
            WebElementUtils.safeClick(driver, RegisterElements.checkbox, 5);
        } catch (Exception e) {
            System.out.println("Failed to click checkbox: " + e.getMessage());
        }
    }

    public void fillForm(String first, String middle, String last, String email, String pass, String confirmPassword) {
        setField("firstname", first);
        setField("middlename", middle);
        setField("lastname", last);
        setField("email", email);
        setField("password", pass);
        setField("confirmpassword", confirmPassword);
        checkboxRM();

        clickRegisterButton();

        String fullName = first + " " + middle + " " + last;

        CredentialsUtils.saveCredential("email", email);
        CredentialsUtils.saveCredential("password", pass);
        CredentialsUtils.saveCredential("fullName", fullName);
    }

}