package core.elements.account;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPageElements {
    public LogInPageElements(){
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(id="email")
    public WebElement email;

    @FindBy(id="pass")
    public WebElement password;

    @FindBy(xpath ="//input[@name='persistent_remember_me']")
    public WebElement remember_me_chB;

    @FindBy(id="send2")
    public WebElement submitButton;

}
