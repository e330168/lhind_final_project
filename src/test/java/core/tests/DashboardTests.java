package core.tests;

import core.pages.cookies.CookieConsentPage;
import core.pages.dashboard.DashboardPage;
import core.pages.dashboard.MyWishListPage;
import core.pages.menu.SalePage;
import core.pages.menu.WomenPage;
import core.utils.TestBase;
import core.utils.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardTests extends TestBase {


    @Test
    public void testProductList() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToWomenViewAll();

        List<WomenPage> items = dashboardPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");

        int itemsCount = items.size();
        Assert.assertEquals(itemsCount, 11, "Total number of products in list should be 11.");
    }

    //T3
    @Test
    public void hoverWomenProduct() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToWomenViewAll();

        WomenPage womenProduct = dashboardPage.getProductItems().get(3);
        System.out.println(womenProduct.getName());

        //        .no-touch .product-image:hover {
        //            border-color: #e26703;
        //        }
        String styleBefore = womenProduct.getHoverStyle().getCssValue("border-color");

        //        rgb(237, 237, 237)
        //        #ededed
        System.out.println("Before hover border-color: " + styleBefore);

        UIActions.hoverOver(womenProduct.getImage());

        //        rgb(51, 153, 204)
        //        #3399cc
        String styleAfter = womenProduct.getHoverStyle().getCssValue("border-color");
        System.out.println("After hover border-color: " + styleAfter);
        Assert.assertNotEquals(styleBefore, styleAfter, "Border color should change on hover.");
    }

    @Test
    public void testMenProductList() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToMenViewAll();

        List<WomenPage> items = dashboardPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");
        Assert.assertEquals(items.size(), 12, "Total number of products in list should be 12.");
    }

    //T5
    @Test
    public void checkPageFilters() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToMenViewAll();
        dashboardPage.selectColor();

        List<WomenPage> products = dashboardPage.getProductItemsMen();
        Assert.assertEquals(products.size(), 3);
        products.forEach(product -> {
            Assert.assertEquals(product.getBorderOfSelectedColor(), "rgb(51, 153, 204)", "Border of selected color should be rgb(51, 153, 204).");
        });

        driver.navigate().back();
        dashboardPage.clickOnPriceFilter();

        int priceFilterCount = dashboardPage.priceFilterCount();
        int totalProducts = dashboardPage.getProductItems().size();

        Assert.assertEquals(totalProducts, 3, "Total products displayed count should be 3.");
        Assert.assertEquals(totalProducts, priceFilterCount, "Total products displayed count should be 3.");

        Assert.assertTrue(dashboardPage.areAllPricesInRangeAfterFiltering());
    }

    //T4
    @Test
    public void checkSaleProductsStyle() {
        logIn();
        SalePage salePage = new SalePage(driver, wait);
        salePage.goToSaleViewAll();
        salePage.getProductItems();
        salePage.checkMultiplePrices();

        List<WomenPage> products = salePage.getProductItems();

        Assert.assertEquals(products.size(), 4);

        products.forEach(product -> {
            Assert.assertTrue(
                    product.areShownMultiplePrices(),
                    "Multiple prices (original + discounted) should be visible."
            );

            Assert.assertTrue(
                    product.checkOldPriceStyle(),
                    "Old price should have color rgba(160, 160, 160, 1) and line-through decoration."
            );

            Assert.assertTrue(
                    product.checkNewPriceStyle(),
                    "Final price should have color rgba(51, 153, 204) and no line-through decoration."
            );
        });
    }

    //T6
    @Test
    public void checkSortedByPriceAnd2WishListSelected(){
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToWomenViewAll();


        List<Double> prices = dashboardPage.getProductItems().stream()
                .map(WomenPage::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Unsorted prices: " + prices);

        dashboardPage.clickSortByDropDown();

        List<Double> pricesR = dashboardPage.getProductItems().stream()
                .map(WomenPage::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        List<Double> reg=dashboardPage.getSortedProduct();

        Assert.assertEquals(pricesR, reg, "Prices are not sorted in ascending order.");

        dashboardPage.addToWishList(0);
        driver.navigate().to("https://ecommerce.tealiumdemo.com/women.html?dir=asc&order=price");
        dashboardPage.getFreshProductItems();
        dashboardPage.addToWishList(1);

        int nrWishListProd=dashboardPage.nrItemsWishListSubMenu();
        Assert.assertEquals(nrWishListProd, 2, "Wishlist count does not match expected.");
    }

    //T7
    @Test(dependsOnMethods = {"checkSortedByPriceAnd2WishListSelected"})
    public void ShoppingCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement optinCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("privacy_pref_optin")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optinCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(optinCheckbox)).click();

        MyWishListPage myWishListPage = new MyWishListPage(driver, wait);
        myWishListPage.goToMyWishList();

//        if(!driver.getCurrentUrl().contains("wishlist")){
//            CookieConsentPage cookiePage = new CookieConsentPage();
//            cookiePage.acceptCookies(wait);
//        }
//        myWishListPage.test6();
    }


    @Test
    public void ShoppingCart1(){
        logIn();
        MyWishListPage myWishListPage = new MyWishListPage(driver, wait);
        myWishListPage.test6();
    }


}