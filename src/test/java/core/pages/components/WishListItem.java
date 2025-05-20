package core.pages.components;


import core.elements.wishList.WishListElements;
import org.openqa.selenium.WebElement;

public class WishListItem {
    private final WebElement rowRoot;
    private final WishListElements elements;

    public WishListItem(WebElement rowRoot) {
        this.rowRoot = rowRoot;
        this.elements = new WishListElements(rowRoot);
    }

    public WebElement getRowRoot() {
        return rowRoot;
    }

    public WebElement addToCartButton() {
        return elements.addToCartButton;
    }

    public String getProductName() {
        return elements.productItemElements.productName.getText();
    }

    public String getPrice() {
        return elements.productItemElements.price.getText();
    }

    public WebElement getImage() {
        return elements.productItemElements.image;
    }

}

