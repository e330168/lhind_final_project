package core.pages.components;

import core.elements.cart.CartElements;
import org.openqa.selenium.WebElement;

public class CartItem {
    private final CartElements elements;
    private WebElement cartItemRow;

    public CartItem(WebElement cartItemRow) {
        this.cartItemRow = cartItemRow;
        this.elements = new CartElements(cartItemRow);
    }

    public WebElement getCartItemRow() {
        return cartItemRow;
    }

    public String getName() {
        return elements.productElements.productName.getText();
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