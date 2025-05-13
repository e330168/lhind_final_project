package core.elements.menu;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuElements {
    public MainMenuElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-target-element='#header-account']")
    public WebElement accountMenu;

    @FindBy(xpath = "//div[@class='links'][1]//a[text()='My Account']")
    public WebElement accountSubMenu;

    @FindBy(xpath = "//a[@title='Register']")
    public WebElement registerSubMenu;

    @FindBy(linkText = "Log In")
    public WebElement logInSubMenu;

    @FindBy(linkText = "Log Out")
    public WebElement logOutSubMenu;
}
