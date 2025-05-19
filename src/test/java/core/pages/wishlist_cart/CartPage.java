package core.pages.wishlist_cart;

import core.elements.cart.CartElements;
import core.elements.cart.WishListItem;
import core.utils.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private double parsePrice(String text) {
        return Double.parseDouble(text.replace("$", "").replace(",", "").trim());
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

    public void deleteFirstItemFromCart() {
        List<WishListItem> items = getCartItems();
        if (!items.isEmpty()) {
            WishListItem firstItem = items.get(0);
            firstItem.clickDelete().click();
            wait.until(ExpectedConditions.stalenessOf(firstItem.getRowRoot()));
            System.out.println("First item removed from cart.");
        } else {
            System.out.println("Cart is already empty.");
        }
    }

}

