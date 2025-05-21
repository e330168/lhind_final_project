package core.elements.dashboard;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ProductElements {
    public ProductElements(WebElement productRoot) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productRoot), this);
    }

    @FindBy(css = ".product-name")
    public WebElement productName;

    @FindBy(css="img[id*='collection']")
    public WebElement image;

    @FindBy(css = "[id*='product-p']")
    public WebElement price;

    @FindBy(css="[id*='old']")
    public WebElement oldPrice;

    @FindBy(css = ".product-image")
    public WebElement hoverStyle;

    @FindBy(xpath = "//a[contains(@name, 'black')]")
    public WebElement selectedColor;

    @FindBy(css=".link-wishlist")
    public WebElement addToWishListLink;

}