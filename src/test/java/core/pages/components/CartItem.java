package core.pages.components;

import core.elements.cart.CartElements;
import org.openqa.selenium.WebElement;

public class CartItem {
    private final CartElements elements;
    private final WebElement rowRoot;

    public CartItem(WebElement rowRoot) {
        this.rowRoot = rowRoot;
        this.elements = new CartElements(rowRoot);
    }

    public WebElement getRowRoot() {
        return rowRoot;
    }

    public String getName() {
        return elements.ProductElements.productName.getText();
    }

    public WebElement quantityInput() {
        return elements.quantityInput;
    }

    public void setQuantity(int quantity) {
        WebElement qtyInput = elements.quantityInput;
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
    }

    public void clickUpdate() {
        elements.updateQuantity.click();
    }

    public double getSubtotalPrice() {
        String priceText = elements.subtotals.getText();
        priceText = priceText.replace(",", "").replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public WebElement clickDelete() {
        return elements.deleteBin;
    }
}