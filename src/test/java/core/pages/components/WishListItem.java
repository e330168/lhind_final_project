package core.pages.components;


import core.elements.wishList.WishListElements;
import org.openqa.selenium.WebElement;

public class WishListItem {
    private final WishListElements elements;

    public WishListItem(WebElement wishListItemRow) {
        this.elements = new WishListElements(wishListItemRow);
    }

    public WebElement addToCartButton() {
        return elements.addToCartButton;
    }

    public String getProductName() {
        return elements.ProductElements.productName.getText();
    }

    public String getPrice() {
        return elements.ProductElements.price.getText();
    }

    public WebElement getImage() {
        return elements.ProductElements.image;
    }

}

