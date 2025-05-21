package core.pages.cart;

import core.elements.dashboard.DashboardElements;
import core.pages.components.CartItem;
import core.utils.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePageObject {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private DashboardElements dashboard;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.dashboard = new DashboardElements();
    }

    public List<CartItem> getCartItems() {
        List<WebElement> itemContainers = driver.findElements(By.xpath("//table[@id='shopping-cart-table']//tbody//tr"));
        List<CartItem> items = new ArrayList<>();
        for (WebElement container : itemContainers) {
            items.add(new CartItem(container));
        }
        return items;
    }

    public int getCartItemsCount() {
        return getCartItems().size();
    }

    public double getGrandTotal() {
        WebElement grandPrice = driver.findElement(By.xpath("//tfoot//span[@class='price']"));
        String totalText = grandPrice.getText();
        return parsePrice(totalText);
    }

    public double parsePrice(String priceText) {
        String cleaned = priceText.replace(",", "").replace("$", "").trim();
        return Double.parseDouble(cleaned);
    }

    public double verifyCartTotalAfterQuantityUpdate() {
        CartPage cartItem = new CartPage(driver, wait);
        List<CartItem> items = cartItem.getCartItems();
        String valueQuantity=" ";
        if (!items.isEmpty()) {
            CartItem firstItem = items.get(0);
            System.out.println("First Item: " + firstItem);
            firstItem.setQuantity(2);
            firstItem.clickUpdate();

            wait.until(ExpectedConditions.stalenessOf(firstItem.quantityInput()));

            List<CartItem> updatedItems = cartItem.getCartItems();
            CartItem updatedFirstItem = updatedItems.get(0);

            wait.until(ExpectedConditions.textToBePresentInElementValue(updatedFirstItem.quantityInput(), "2"));
            valueQuantity = updatedFirstItem.quantityInput().getAttribute("value");
            System.out.println("Updated Quantity Input :" + valueQuantity);

        }
        return Double.parseDouble(valueQuantity);
    }

    public double subTotalSum () {
            List<CartItem> items = getCartItems();
            double sum = 0.0;
            for (CartItem item : items) {
                System.out.println("Product price: " + item.getSubtotalPrice());
                sum += item.getSubtotalPrice();
            }
            System.out.println("Sum of subTotal prices: " + sum);
            return sum;
    }

    public void deleteFirstItemFromCart() {
        List<CartItem> cartItems = getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Cart is already empty.");
            return;
        }
        CartItem firstItem = cartItems.get(0);
        WebElement staleElement = firstItem.getCartItemRow();

        firstItem.clickDelete().click();
        wait.until(ExpectedConditions.stalenessOf(staleElement));
    }

    public boolean isCartEmptyMessageDisplayed() {
        WebElement emptyMessage = wait.until(ExpectedConditions.visibilityOf(dashboard.emptyCartMessage));
        return emptyMessage.getText().contains("You have no items in your shopping cart.");
    }
}

