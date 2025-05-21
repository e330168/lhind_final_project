package core.elements.wishList;

import core.elements.dashboard.ProductElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class WishListElements {

    public WishListElements(WebElement root) {
        PageFactory.initElements(new DefaultElementLocatorFactory(root), this);
        this.ProductElements = new ProductElements(root);
    }

    public ProductElements ProductElements;

    @FindBy(css = ".button.btn-cart")
    public WebElement addToCartButton;
}
