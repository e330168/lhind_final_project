package core.elements.cart;

import core.elements.dashboard.ProductItemElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishListElements extends ProductItemElements {
    public WishListElements(WebElement webElement) {
        super(webElement);
    }

    @FindBy(css = ".button.btn-cart")
    public WebElement addToCartButton;

    @FindBy(xpath ="//li[contains(@class,'is-media')]")
    public List<WebElement> selectColor;

    @FindBy(xpath ="//ul[@id='configurable_swatch_size']//li[not(contains(@class, 'not-available'))]")
    public List<WebElement> selectSize;

    @FindBy(xpath = "//div[@class='add-to-cart-buttons']//button")
    public WebElement addToCartBF;

    @FindBy(xpath="//td[@class='product-cart-actions']//button")
    public WebElement updateQuantity;

    @FindBy(xpath="//input[@class='input-text qty']")
    public WebElement quantityInput;

    @FindBy(xpath=".//td[@class='product-cart-total']//span[@class='price']")
    public WebElement subtotals;

    @FindBy(xpath=".//td[@class='a-center product-cart-remove last']//a")
    public WebElement deleteBin;
}
