package core.elements.cart;

import core.elements.dashboard.ProductItemElements;
import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class CartElements{

    public CartElements(WebElement root) {
        PageFactory.initElements(new DefaultElementLocatorFactory(root), this);
        this.productItemElements = new ProductItemElements(root);
    }

    public ProductItemElements productItemElements;

    @FindBy(xpath="//td[@class='product-cart-actions']//button")
    public WebElement updateQuantity;

    @FindBy(xpath=".//input[@class='input-text qty']")
    public WebElement quantityInput;

    @FindBy(xpath=".//td[@class='product-cart-total']//span[@class='price']")
    public WebElement subtotals;

    @FindBy(xpath=".//td[@class='a-center product-cart-remove last']//a")
    public WebElement deleteBin;

    @FindBy(xpath=".//tfoot//span[@class='price']")
    public WebElement grandPrice;
}
