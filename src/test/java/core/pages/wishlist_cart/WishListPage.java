package core.pages.wishlist_cart;

import core.elements.cart.WishListItem;
import core.elements.menu.AccountCartMenuElements;
import core.pages.menu.MainMenuPage;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class WishListPage extends BasePageObject {
        private  WebDriver driver;
        private  WebDriverWait wait;
        private  AccountCartMenuElements mainMenu;
        private WishListItem wishListItem;
        private MainMenuPage mainMenuPage;

    public WishListPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.mainMenu = new AccountCartMenuElements();
        this.mainMenuPage = new MainMenuPage(driver, wait);
    }

    public List<WishListItem> getItems() {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='wishlist-table']//tbody//tr"));
        return rows.stream().map(WishListItem::new).collect(Collectors.toList());
    }

    public void clickAddToCartForItem(int index) {
        List<WishListItem> items = getItems();
        if (index >= items.size()) {
            throw new IllegalArgumentException("Index out of range");
        }
        WebElement addToCartBtn = items.get(index).addToCart();
        WaitUtils.waitForVisible(driver, addToCartBtn);
        UIActions.click(driver, addToCartBtn);
    }

    public void addToCart() {
        int itemIndex = 0;

        while (true) {
            try {
                driver.navigate().to("https://ecommerce.tealiumdemo.com/wishlist/");
                WishListPage cartPage = new WishListPage(driver, wait);
                ProductConfigPage productConfigPage = new ProductConfigPage(driver);

                List<WishListItem> freshItems = cartPage.getItems();

                if (freshItems.isEmpty()) {
                    System.out.println("Wishlist is empty. Finished processing all items.");
                    break;
                }
                if (itemIndex >= freshItems.size()) {
                    itemIndex = 0;
                }
                cartPage.clickAddToCartForItem(itemIndex);
                wait.until(ExpectedConditions.urlContains("configure"));

                productConfigPage.selectColor();
                productConfigPage.selectSize();

                productConfigPage.addToCart();
                if (itemIndex ==0) {
                    wait.until(ExpectedConditions.urlContains("/checkout/cart/"));
                }
                System.out.println("Item added to cart.");

            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element. Reloading wishlist.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
