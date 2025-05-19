package core.pages.wishlist_cart;

import core.elements.cart.WishListItem;
import core.elements.menu.AccountCartMenuElements;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class WishListPage extends BasePageObject {
        private  WebDriver driver;
        private  WebDriverWait wait;
        private  AccountCartMenuElements mainMenu;
        private WishListItem wishListItem;

    public WishListPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.mainMenu = new AccountCartMenuElements();
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

    public int getItemCount() {
        return getItems().size();
    }

    public void goToMyWishList() {
        WaitUtils.waitForVisible(driver, mainMenu.accountMenu);
        UIActions.click(driver, mainMenu.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenu.myWishListMenu);
        UIActions.click(driver, mainMenu.myWishListMenu);
    }

    //    public void displayWishItems() {
    //        List<WishListItem> items = getItems();
    //        items.forEach(item -> {
    //            String name = item.getName();
    //            String price = item.getPrice().getText();
    //
    //            System.out.println("Name: " + name);
    //            System.out.println("Image: " + price);
    //            System.out.println(" ");
    //        });
    //    }

    public void forAll() {
        int size = getItems().size();
        for (int i = 0; i < size; i++) {
            try {
                driver.navigate().to("https://ecommerce.tealiumdemo.com/wishlist/index/index/wishlist_id/1702/");
                int currentSize = getItems().size();
                if (i >= currentSize) {
                    System.out.println("Index " + i + " is out of range after page reload. Stopping loop.");
                    break;
                }
                test6(i);
                driver.navigate().back();
            } catch (Exception e) {
                System.out.println("Error on index " + i + ": " + e.getMessage());
            }
        }
    }


    public void test6(int i) {
        List<WishListItem> items = getItems();
        System.out.println("Number of wishlist items: " + items.size());

        if (i < items.size()) {
            WishListItem item = items.get(i);
            System.out.println(item.getName());
            System.out.println(item.getPrice());


//        if (item.addToCart().isDisplayed() && item.addToCart().isEnabled()) {
//            item.addToCartBF().click();
//            wait.until(ExpectedConditions.elementToBeClickable(item.addToCart()));
//            System.out.println("Item " + i + " has been added to the cart");
//        } else {
//            System.out.println("Add To Cart button is not displayed");
//        }
//        }

            wait.until(ExpectedConditions.urlContains("configure"));
        } else {
            System.out.println("Index " + i + " is out of bounds for wishlist items.");
        }
    }


//    public double changeQuantity(){
//        driver.navigate().to("https://ecommerce.tealiumdemo.com/checkout/cart/");
//        wait.until(ExpectedConditions.urlContains("cart"));
//
//        WaitUtils.waitForVisible(driver,wishListItem.quantityInput());
//        UIActions.click(driver, wishListItem.quantityInput());
//
//        wishListItem.quantityInput().clear();
//        wishListItem.quantityInput().sendKeys("2");
//
//        UIActions.click(driver,wishListItem.updateQuantity());
//
//        wait.until(ExpectedConditions.textToBePresentInElementValue(wishListItem.quantityInput(),"2"));
//        String valueQ=wishListItem.quantityInput().getAttribute("value");
//        System.out.println("Updated Quantity Input :"+valueQ);
//        return Double.parseDouble(valueQ);
//    }

//    public double checkTheGrandPrice() {
////        driver.navigate().to("https://ecommerce.tealiumdemo.com/checkout/cart/");
//        List<Double> prices = wishListItem.getSubTotalPrice().stream()
//                .map(p -> Double.parseDouble(p.getText().replace("$", "").trim()))
//                .collect(Collectors.toList());
//
//        int sum = 0;
//        for (int i = 0; i < prices.size(); i++) {
//            sum += prices.get(i);
//        }
//        System.out.println("Amount of subTotal prices: "+sum);
//        return sum;
//    }
//
//    public double getGrandPrice() {
//        String p=wishListItem.getGrandPrice().getText();
//        return Double.parseDouble(p.replace("$", "").trim());
//    }


}
