package core.elements.menu;

import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class WomenPageElements {
    public WomenPageElements(WebElement productRoot) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productRoot), this);
    }

    @FindBy(css = ".product-name")
    public WebElement productName;

//    @FindBy(xpath = "//*[contains(@id, 'product-collection-image')]")
//    @FindBy(xpath = "//img[contains(@id,'product-collection-image')]")
//    @FindBy(xpath = "//ul[contains(@class, 'products-grid')]/descendant::img[contains(@src, 'product')]")
//    @FindBy(css = "[src*='product']")
//    @FindBy(css = "[src*='product'][id*='product']")
//    @FindBy(css = "img[src*='product'][id*='product']")
    @FindBy(css="img[id*='product-collection-image']")
    public WebElement image;

    @FindBy(css = "[id*='product-price']")
    public WebElement price;

    @FindBy(css = ".product-image")
    public WebElement hoverStyle;

    @FindBy(css = ".rating")
    public WebElement rating;

    @FindBy(css = ".add-to-wishlist")
    public WebElement addToWishlistButton;

    @FindBy(xpath = "(//*[contains(@class,'ratings')])/following-sibling::div/a")
    public WebElement viewDetailsButton;

}
