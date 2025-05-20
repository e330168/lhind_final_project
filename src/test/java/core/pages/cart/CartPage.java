package core.pages.cart;

import core.elements.cart.CartElements;
import core.pages.components.CartItem;
import core.utils.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public List<CartItem> getCartItems() {
        List<WebElement> itemContainers = driver.findElements(By.xpath("//table[@id='shopping-cart-table']//tbody//tr"));
        List<CartItem> items = new ArrayList<>();
        for (WebElement container : itemContainers) {
            items.add(new CartItem(container));
        }
        return items;
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
        String valueQ=" ";
//        wait.until(ExpectedConditions.urlContains("cart"));
//        WebElement qtyInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//input[@class='input-text qty']")));
        if (!items.isEmpty()) {
            CartItem firstItem = items.get(0);
            System.out.println("First Item: " + firstItem);
            firstItem.setQuantity(2);
            firstItem.clickUpdate();

            wait.until(ExpectedConditions.stalenessOf(firstItem.quantityInput()));

            List<CartItem> updatedItems = cartItem.getCartItems();
            CartItem updatedFirstItem = updatedItems.get(0);

            wait.until(ExpectedConditions.textToBePresentInElementValue(updatedFirstItem.quantityInput(), "2"));
            valueQ = updatedFirstItem.quantityInput().getAttribute("value");
            System.out.println("Updated Quantity Input :" + valueQ);

        }
        return Double.parseDouble(valueQ);
    }

    public double checkTheGrandPrice () {
            List<CartItem> items = getCartItems();
            double sum = 0.0;
            for (CartItem item : items) {
                System.out.println("Amount item: " + item.getSubtotalPrice());
                sum += item.getSubtotalPrice();
            }
            System.out.println("Amount of subTotal prices: " + sum);
            return sum;
        }

    public int deleteAllItemsFromCart() {
        By itemRowsLocator = By.xpath("//table[@id='shopping-cart-table']//tbody//tr");

        List<WebElement> rows = driver.findElements(itemRowsLocator);
        int previousCount = rows.size();

        while (previousCount > 0) {
            List<CartItem> items = getCartItems();
            CartItem firstItem = items.get(0);
            firstItem.clickDelete().click();

            int finalPreviousCount = previousCount;
            wait.until(driver -> {
                List<WebElement> updatedRows = driver.findElements(itemRowsLocator);
                return updatedRows.size() < finalPreviousCount;
            });
            previousCount--;
            System.out.println("Item removed. Remaining: " + previousCount);
        }

        System.out.println("Cart is now empty.");
        return previousCount;
    }

    public boolean isCartEmptyMessageDisplayed() {
        WebElement emptyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-empty']//p")));
        return emptyMessage.getText().contains("You have no items in your shopping cart.");
    }

    public boolean removeFirstProductFromCart() {
        List<CartItem> itemsBefore = getCartItems();
        int countBefore = itemsBefore.size();

        if (countBefore == 0) {
            System.out.println(" Cart is already empty.");
            return false;
        }
        CartItem firstItem = itemsBefore.get(0);
        firstItem.clickDelete().click();
        wait.until(ExpectedConditions.stalenessOf(firstItem.getRowRoot()));
        int countAfter = itemsBefore.size();

        return countAfter == countBefore - 1;
    }
}

