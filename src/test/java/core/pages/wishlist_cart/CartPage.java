package core.pages.wishlist_cart;

import core.elements.cart.CartElements;
import core.elements.cart.WishListItem;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePageObject {
    private CartElements cartPageElements;
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        cartPageElements=new CartElements();
    }

    public List<WishListItem> getCartItems() {
        List<WebElement> itemContainers = driver.findElements(By.xpath("//table[@id='shopping-cart-table']//tbody//tr"));
        List<WishListItem> items = new ArrayList<>();
        for (WebElement container : itemContainers) {
            items.add(new WishListItem(container));
        }
        return items;
    }

    public double getGrandTotal() {
        WebElement totalElement = cartPageElements.grandPrice;
        String totalText = totalElement.getText();
        return parsePrice(totalText);
    }

    public double parsePrice(String priceText) {
        String cleaned = priceText.replace(",", "").replace("$", "").trim();
        return Double.parseDouble(cleaned);
    }

    public double verifyCartTotalAfterQuantityUpdate() {
        CartPage cartPage = new CartPage(driver, wait);
        List<WishListItem> items = cartPage.getCartItems();
        String valueQ=" ";
        if (!items.isEmpty()) {
            WishListItem firstItem = items.get(0);
            firstItem.setQuantity(2);
            firstItem.clickUpdate();

            wait.until(ExpectedConditions.stalenessOf(firstItem.quantityInput()));

            List<WishListItem> updatedItems = cartPage.getCartItems();
            WishListItem updatedFirstItem = updatedItems.get(0);

            wait.until(ExpectedConditions.textToBePresentInElementValue(updatedFirstItem.quantityInput(), "2"));
            valueQ = updatedFirstItem.quantityInput().getAttribute("value");
            System.out.println("Updated Quantity Input :" + valueQ);

        }
        return Double.parseDouble(valueQ);
    }

    public double checkTheGrandPrice () {
            List<WishListItem> items = getCartItems();
            double sum = 0.0;
            for (WishListItem item : items) {
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
            List<WishListItem> items = getCartItems();
            WishListItem firstItem = items.get(0);
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
        List<WishListItem> itemsBefore = getCartItems();
        int countBefore = itemsBefore.size();

        if (countBefore == 0) {
            System.out.println(" Cart is already empty.");
            return false;
        }
        WishListItem firstItem = itemsBefore.get(0);
        firstItem.clickDelete().click();
        wait.until(ExpectedConditions.stalenessOf(firstItem.getRowRoot()));
        int countAfter = itemsBefore.size();

        return countAfter == countBefore - 1;
    }
}

