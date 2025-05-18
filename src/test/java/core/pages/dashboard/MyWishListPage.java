package core.pages.dashboard;

import core.elements.dashboard.MyWishListElementsPage;
import core.elements.menu.MainMenuElements;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyWishListPage extends BasePageObject {
        private WebDriver driver;
        private WebDriverWait wait;
        private MainMenuElements mainMenu;
        private MyWishListElementsPage wishEle;

    public MyWishListPage(WebDriver driver, WebDriverWait wait) {
            super(driver);
            this.driver = driver;
            this.wait = wait;
            this.mainMenu=new MainMenuElements();
            this.wishEle=new MyWishListElementsPage();
        }


    public void goToMyWishList() {
        WaitUtils.waitForVisible(driver, mainMenu.accountMenu);
        UIActions.click(driver, mainMenu.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenu.myWishListMenu);
        UIActions.click(driver, mainMenu.myWishListMenu);
    }

    public void clickToAdToCartButton() {
        WaitUtils.waitForVisible(driver,wishEle.addToCartButton );
        UIActions.click(driver, wishEle.addToCartButton);
    }

    public void test6(){
//        driver.navigate().to("https://ecommerce.tealiumdemo.com/wishlist/index/index/wishlist_id/1681/");
//        driver.getCurrentUrl();
//        System.out.println(driver.getCurrentUrl());
        goToMyWishList();
//        System.out.println(driver.getCurrentUrl());
        clickToAdToCartButton();
//        System.out.println(driver.getCurrentUrl());
        wait.until(ExpectedConditions.urlContains("configure"));

        WaitUtils.waitForVisible(driver,wishEle.selectColor.get(0));
        UIActions.click(driver, wishEle.selectColor.get(0));
        System.out.println(wishEle.selectColor.get(0).getAttribute("name"));

        WaitUtils.waitForVisible(driver,wishEle.selectSize.get(3));
        UIActions.click(driver, wishEle.selectSize.get(3));
        System.out.println(wishEle.selectSize.get(3).getAttribute("name"));

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        if (wishEle.addToCartBF.isDisplayed() && wishEle.addToCartBF.isEnabled()) {
            wishEle.addToCartBF.click();
        } else {
            System.out.println("Add To Cart button is not displayed");
        }
    }


    public double changeQuantity(){
        driver.navigate().to("https://ecommerce.tealiumdemo.com/checkout/cart/");
        wait.until(ExpectedConditions.urlContains("cart"));

        WaitUtils.waitForVisible(driver,wishEle.quantityInput );
        UIActions.click(driver, wishEle.quantityInput);

        wishEle.quantityInput.clear();
        wishEle.quantityInput.sendKeys("2");

        UIActions.click(driver,wishEle.updateQuantity);

        wait.until(ExpectedConditions.textToBePresentInElementValue(wishEle.quantityInput,"2"));
        String valueQ=wishEle.quantityInput.getAttribute("value");
        System.out.println("Updated Quantity Input :"+valueQ);
        return Double.parseDouble(valueQ);
    }

    public double checkTheGrandPrice() {
//        driver.navigate().to("https://ecommerce.tealiumdemo.com/checkout/cart/");
        List<Double> prices = wishEle.subtotals.stream()
                .map(p -> Double.parseDouble(p.getText().replace("$", "").trim()))
                .collect(Collectors.toList());

        int sum = 0;
        for (int i = 0; i < prices.size(); i++) {
            sum += prices.get(i);
        }
        System.out.println("Amount of subTotal prices: "+sum);
        return sum;
    }

    public double getGrandPrice() {
        String p=wishEle.grandPrice.getText();
        return Double.parseDouble(p.replace("$", "").trim());
    }


}
