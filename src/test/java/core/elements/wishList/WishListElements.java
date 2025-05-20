package core.elements.wishList;

import core.elements.dashboard.ProductItemElements;
import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;


public class WishListElements {

    public WishListElements(WebElement root) {
        PageFactory.initElements(new DefaultElementLocatorFactory(root), this);
        this.productItemElements = new ProductItemElements(root);
    }

    public ProductItemElements productItemElements;

    @FindBy(css = ".button.btn-cart")
    public WebElement addToCartButton;

}
