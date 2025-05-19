package core.elements.account;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterElements {
    public RegisterElements(){
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(id = "firstname")
    public  WebElement firstName;

    @FindBy(id = "middlename")
    public WebElement middleName;

    @FindBy(id="lastname")
    public  WebElement lastName;

    @FindBy(id="email_address")
    public  WebElement emailAddress;

    @FindBy(id="password")
    public  WebElement password;

    @FindBy(id="confirmation")
    public  WebElement confirmP;

    @FindBy(name="persistent_remember_me")
    public  WebElement checkbox;

    @FindBy(xpath = "//button[@title='Register']")
    public  WebElement registerButton;

    @FindBy(xpath = "//li[@class='success-msg']//span")
    public  WebElement verifyRegistration;

}
