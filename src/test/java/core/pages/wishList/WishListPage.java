package core.pages.wishList;

import core.elements.navigation.AccountMenuElements;
import core.pages.components.CartItem;
import core.pages.components.WishListItem;
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
        private  AccountMenuElements mainMenu;
        private MainMenuPage mainMenuPage;

    public WishListPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.mainMenu = new AccountMenuElements();
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
        WebElement addToCartBtn = items.get(index).addToCartButton();
        WaitUtils.waitForVisible(driver, addToCartBtn);
        UIActions.click(driver, addToCartBtn);
    }

    public void addToCart() {
        WishListPage wishListPage = new WishListPage(driver, wait);
        ProductConfigPage productConfigPage = new ProductConfigPage(driver);

        int totalItems = wishListPage.getItems().size();

        for (int i = 0; i < totalItems; i++) {
            List<WishListItem> currentItems = wishListPage.getItems();

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
                System.out.println("All items processed. Staying on cart page.");
            }
        }
    }



}
