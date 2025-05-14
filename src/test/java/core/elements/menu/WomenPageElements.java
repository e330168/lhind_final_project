package core.elements.menu;

import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WomenPageElements {
    public WomenPageElements(){
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(css = ".product-name")
    public WebElement productName;

    @FindBy(xpath = "//*[contains(@id, 'product-collection-image')]")
    public WebElement image;

    //    @FindBy(id = ".product-price")
    @FindBy(css = "[id*='product-price']")
    public WebElement productPrice;

    @FindBy(css = ".rating")
    public WebElement rating;

    @FindBy(css = ".add-to-wishlist")
    public WebElement addToWishlistButton;

    @FindBy(xpath = "(//*[contains(@class,'ratings')])/following-sibling::div/a")
    public WebElement viewDetailsButton;

    @FindBy(css = ".product-image:hover")
    public WebElement hoverStyle;

    @FindBy(xpath = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li")
    public List<WebElement> productItems;

}
