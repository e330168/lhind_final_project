package core.pages.account;

import core.elements.account.RegisterPageElements;
import core.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends RegisterPageElements {
    BasePageObject basePageObject = new BasePageObject(DriverProvider.getDriver());
    RegisterPageElements registerPageElements =  new RegisterPageElements();
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

    public void setFirstName(String firstName){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.firstName)
                .sendKeys(firstName);
    }

    public void setMiddleName(String middleName){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.middleName)
                .sendKeys(middleName);
    }

    public void setLastName(String lastName) {
        basePageObject
                .getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.lastName, lastName, 2);
    }

    public void setEmail(String email){
        basePageObject
                .getWaitUtils()
                .waitForElementVisible(registerPageElements.emailAddress)
                .sendKeys(email);
    }

    public void setPassword(String password){
        basePageObject
                .getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.password,password,2);

    }

    public void setConfirmPassword(String confirmPassword){
        basePageObject
                .getWebElementUtils()
                .sendKeysToElementWithWait(registerPageElements.confirmP,confirmPassword,2);
    }

    public void checkboxRM() {
        if (!WebElementUtils.isElementDisplayed(registerPageElements.checkbox)) return;

        try {
            WebElementUtils.safeClick(driver, registerPageElements.checkbox, 5);
        } catch (Exception e) {
            System.out.println("Failed to click checkbox: " + e.getMessage());
        }
    }

    public void fillForm(String first, String middle, String last, String email, String pass, String confirmPassword) {
        setFirstName(first);
        setMiddleName(middle);
        setLastName(last);
        setEmail(email);
        setPassword(pass);
        setConfirmPassword(confirmPassword);
        checkboxRM();

        clickRegisterButton();

        String fullName = first + " " + middle + " " + last;

        CredentialsUtils.saveCredential("email", email);
        CredentialsUtils.saveCredential("password", pass);
        CredentialsUtils.saveCredential("fullName", fullName);
    }

    public String getSuccessMessage() {
        return verifyRegistration.getText();
    }
}