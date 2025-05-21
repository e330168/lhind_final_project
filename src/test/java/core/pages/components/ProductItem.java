package core.pages.components;

import core.elements.dashboard.ProductElements;
import org.openqa.selenium.WebElement;

public class ProductItem {
    private final ProductElements productElements;

    public ProductItem(WebElement productElement) {
        this.productElements = new ProductElements(productElement);
    }

    public String getName() {
        return productElements.productName.getText();
    }

    public String getPrice() {
        return productElements.price.getText();
    }

    public String getOldPrice() {
        return productElements.oldPrice.getText();
    }

    public WebElement getImage() {
        return productElements.image;
    }

    public String getImageSrc() {
        return productElements.image.getAttribute("src");
    }

    public WebElement getHoverStyle() {
        return productElements.hoverStyle;
    }

    public String getBorderOfSelectedColor() {
        return productElements.selectedColor.getCssValue("border-color");
    }

    public WebElement getOriginalPriceStyle() {
        return productElements.oldPrice;
    }

    public WebElement getFinalPrice() {
        return productElements.price;
    }

    public WebElement getWishListButton() {
        return productElements.addToWishListLink;
    }

    public boolean areShownMultiplePrices() {
        return !getOldPrice().isEmpty() && !getPrice().isEmpty();
    }

    public boolean checkOldPriceStyle() {
        String colStyle = getOriginalPriceStyle().getCssValue("color");
        String decStyle = getOriginalPriceStyle().getCssValue("text-decoration-line");

        return colStyle.equals("rgba(160, 160, 160, 1)") && decStyle.equals("line-through");
    }

    public boolean checkNewPriceStyle() {
        String colStyle = getFinalPrice().getCssValue("color");
        String decStyle = getFinalPrice().getCssValue("text-decoration-line");

        return colStyle.equals("rgba(51, 153, 204, 1)") && !decStyle.equals("line-through");
    }
}