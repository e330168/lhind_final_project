package core.elements.navigation;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountCartMenuElements {
    public AccountCartMenuElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath = "//a[@data-target-element='#header-account']")
    public WebElement accountMenu;

    @FindBy(xpath = "//div[@class='links']//li[2]//a")
    public WebElement myWishListMenu;

    @FindBy(xpath = "//div[@class='links']//li[3]//a")
    public WebElement cartMenu;

    @FindBy(xpath = "//a[@title='Register']")
    public WebElement registerSubMenu;

    @FindBy(linkText = "Log In")
    public WebElement logInSubMenu;

    @FindBy(linkText = "Log Out")
    public WebElement logOutSubMenu;

    @FindBy(xpath ="//table[@id='shopping-cart-table']//tbody//tr")
    public List<WebElement> productsOnCart;

    @FindBy(xpath="//span[@class='count']")
    public WebElement nrOfCartProd;
}
