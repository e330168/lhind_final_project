package core.elements.cart;

import core.elements.dashboard.ProductItemElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class WishListElements extends ProductItemElements {
    public WishListElements(WebElement wishlistItemRoot) {
        super(wishlistItemRoot);
        // No need to call PageFactory.initElements here again!
    }

//    @FindBy(xpath="//div[@class='cart-cell'][1]//button")
    @FindBy(css = ".button.btn-cart")
    public WebElement addToCartButton;

//    @FindBy(xpath ="//ul[@id='configurable_swatch_color']//a")
//@FindBy(css="[class*='is-media']")
    @FindBy(id = "option22")
//    public List<WebElement> selectColor;
    public WebElement selectColor;

    @FindBy(xpath ="//ul[@id='configurable_swatch_size']//li//a")
    public List<WebElement> selectSize;

    @FindBy(xpath = "//div[@class='add-to-cart-buttons']//button")
    public WebElement addToCartBF;

    @FindBy(xpath="//td[@class='product-cart-actions']//button")
    public WebElement updateQuantity;

    @FindBy(xpath="//input[@class='input-text qty']")
    public WebElement quantityInput;

    @FindBy(xpath="//td[@class='product-cart-total']//span[@class='price']")
    public List<WebElement> subtotals;

    @FindBy(xpath="//tfoot//span[@class='price']")
    public WebElement grandPrice;
}
