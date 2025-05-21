package core.elements.navigation;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountMenuElements {
    public AccountMenuElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-target-element='#header-account']")
    public WebElement accountMenu;

    @FindBy(xpath = "//div[@class='links']//li[2]//a")
    public WebElement myWishListSubMenu;

    @FindBy(xpath = "//div[@class='links']//li[3]//a")
    public WebElement cartSubMenu;

    @FindBy(xpath = "//a[@title='Register']")
    public WebElement registerSubMenu;

    @FindBy(linkText = "Log In")
    public WebElement logInSubMenu;

    @FindBy(linkText = "Log Out")
    public WebElement logOutSubMenu;

}
