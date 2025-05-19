package core.elements.cart;

import org.openqa.selenium.WebElement;

public class WishListItem {
    private final WishListElements elements;
    private final WebElement rowRoot;

    public WishListItem(WebElement rowRoot) {
        this.rowRoot = rowRoot;
        this.elements = new WishListElements(rowRoot);
    }

    public WebElement getRowRoot() {
        return rowRoot;
    }

    public String getName() {
        return elements.productName.getText();
    }

    public WebElement addToCart() {return elements.addToCartButton;}

    public WebElement quantityInput() {
        return elements.quantityInput;
    }
    public void setQuantity(int quantity) {
        WebElement qtyInput = elements.quantityInput;
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
    }

    public void clickUpdate() {
        WebElement updateButton = elements.updateQuantity;
        updateButton.click();
    }

    public double getSubtotalPrice() {
        String priceText = elements.subtotals.getText();
        return parsePrice(priceText);
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    public WebElement clickDelete() {
        return elements.deleteBin;
    }

//
//    public WebElement addToCartBF() {
//        return elements.addToCartButton;
//    }
//
//    public double getGrandTotal() {
//        String totalText = elements.price.getText();
//        return parsePrice(totalText);
//    }
//
//    public WebElement getPrice() {
//        return elements.price;
//    }
//    public WebElement getColor() {
//        return elements.selectColor;
//    }
//
//    public List<WebElement> getSizeP() {
//        return elements.selectSize;
//    }

}
