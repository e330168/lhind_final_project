package core.elements.cart;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class WishListItem {
    private final WishListElements elements;

//    public WishListItem(WebElement rowRoot) {
//        PageFactory.initElements(new DefaultElementLocatorFactory(rowRoot), this);
//    }

    public WishListItem getItem(WebElement root) {
        return new WishListItem(root);
    }

    public WishListItem(WebElement root) {
        this.elements = new WishListElements(root);
    }

    public String getName() {
        return elements.productName.getText();
    }

    public WebElement getPrice() {
        return elements.price;
    }

//    public List<WebElement> getColor() {
//        return elements.selectColor;
//    }

    public WebElement getColor() {
        return elements.selectColor;
    }

    public List<WebElement> getSizeP() {
        return elements.selectSize;
    }

    public WebElement addToCart() {return elements.addToCartButton;}

    public WebElement addToCartBF() {
        return elements.addToCartButton;
    }

    public WebElement updateQuantity() {
        return elements.addToCartButton;
    }

    public WebElement quantityInput() {
        return elements.quantityInput;
    }

    public List<WebElement> getSubTotalPrice() {
        return elements.subtotals;
    }

    public WebElement getGrandPrice() {
        return elements.grandPrice;
    }


}
