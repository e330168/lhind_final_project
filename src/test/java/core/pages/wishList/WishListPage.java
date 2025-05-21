package core.pages.wishList;

import core.pages.components.WishListItem;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class WishListPage extends BasePageObject {
        private final WebDriver driver;
        private final WebDriverWait wait;

    public WishListPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public List<WishListItem> getWishListItems() {
        List<WebElement> wishListItems = driver.findElements(By.xpath("//table[@id='wishlist-table']//tbody//tr"));
        return wishListItems.stream().map(WishListItem::new).collect(Collectors.toList());
    }

    public void clickAddToCartForItem(int index) {
        List<WishListItem> wishListItems = getWishListItems();
        if (index >= wishListItems.size()) {
            throw new IllegalArgumentException("Index out of range");
        }
        WebElement addToCartBtn = wishListItems.get(index).addToCartButton();
        System.out.println(wishListItems.get(index).getProductName()+" "+ wishListItems.get(index).getPrice());
        WaitUtils.waitForVisible(driver, addToCartBtn);
        UIActions.click(driver, addToCartBtn);
    }

    public void addToCart() {
        WishListPage wishListPage = new WishListPage(driver, wait);
        ProductConfigPage productConfigPage = new ProductConfigPage(driver,wait);

        int totalItems = wishListPage.getWishListItems().size();

        for (int i = 0; i < totalItems; i++) {
            List<WishListItem> currentItems = wishListPage.getWishListItems();

            if (currentItems.isEmpty()) {
                System.out.println("Wishlist is empty.");
                break;
            }

            wishListPage.clickAddToCartForItem(0);
            wait.until(ExpectedConditions.urlContains("configure"));

            productConfigPage.selectColor();
            productConfigPage.selectSize();
            productConfigPage.addToCart();

            wait.until(ExpectedConditions.urlContains("/checkout/cart"));

            if (i < totalItems - 1) {
                driver.navigate().back();
                wait.until(ExpectedConditions.urlContains("/wishlist"));
            } else {
                driver.navigate().forward();
                System.out.println("All products are moved from wishlist to cart.");
            }
        }
    }

}
