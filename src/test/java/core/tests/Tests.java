package core.tests;

import core.pages.dashboard.ProductsGridPage;
import core.pages.wishlist_cart.ProductConfigPage;
import core.pages.wishlist_cart.WishListPage;
import core.pages.dashboard.SalePage;
import core.pages.dashboard.ProductItemPage;
import core.utils.TestBase;
import core.utils.UIActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class Tests extends TestBase {

    @Test
    public void testProductList() {
        logIn();
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();

        List<ProductItemPage> items = ProductsGridPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");

        int itemsCount = items.size();
        Assert.assertEquals(itemsCount, 11, "Total number of products in list should be 11.");
    }

    //T3
    @Test
    public void hoverWomenProduct() {
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();

        ProductItemPage womenProduct = ProductsGridPage.getProductItems().get(3);
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
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToMenViewAll();

        List<ProductItemPage> items = ProductsGridPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");
        Assert.assertEquals(items.size(), 12, "Total number of products in list should be 12.");
    }

    //T4
    @Test
    public void checkSaleProductsStyle() {
        logIn();
        SalePage salePage = new SalePage(driver, wait);
        salePage.goToSaleViewAll();
        salePage.getProductItems();
        salePage.checkMultiplePrices();

        List<ProductItemPage> products = salePage.getProductItems();

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

    //T5
    @Test
    public void checkPageFilters() {
        logIn();
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToMenViewAll();
        ProductsGridPage.selectColor();

        List<ProductItemPage> products = ProductsGridPage.getProductItemsMen();
        Assert.assertEquals(products.size(), 3);
        products.forEach(product -> {
            Assert.assertEquals(product.getBorderOfSelectedColor(), "rgb(51, 153, 204)", "Border of selected color should be rgb(51, 153, 204).");
        });

        driver.navigate().back();
        ProductsGridPage.clickOnPriceFilter();

        int priceFilterCount = ProductsGridPage.priceFilterCount();
        int totalProducts = ProductsGridPage.getProductItems().size();

        Assert.assertEquals(totalProducts, 3, "Total products displayed count should be 3.");
        Assert.assertEquals(totalProducts, priceFilterCount, "Total products displayed count should be 3.");

        Assert.assertTrue(ProductsGridPage.areAllPricesInRangeAfterFiltering());
    }


    //T6
    @Test
    public void checkSortedByPriceAnd2WishListSelected(){
        logIn();
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();


        List<Double> prices = ProductsGridPage.getProductItems().stream()
                .map(ProductItemPage::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Unsorted prices: " + prices);

        ProductsGridPage.clickSortByDropDown();

        List<Double> pricesR = ProductsGridPage.getProductItems().stream()
                .map(ProductItemPage::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        List<Double> reg=ProductsGridPage.getSortedProduct();

        Assert.assertEquals(pricesR, reg, "Prices are not sorted in ascending order.");

        ProductsGridPage.addToWishList(0);
        driver.navigate().to("https://ecommerce.tealiumdemo.com/women.html?dir=asc&order=price");
        ProductsGridPage.getFreshProductItems();
        ProductsGridPage.addToWishList(1);

        int nrWishListProd=ProductsGridPage.nrItemsWishListSubMenu();
        Assert.assertEquals(nrWishListProd, 2, "Wishlist count does not match expected.");
    }

    //T7
    //    @Test(dependsOnMethods = {"checkSortedByPriceAnd2WishListSelected"})
//    @Test
//    public void shoppingCart(){
//        logIn();
//        WishListPage myWishListPage = new WishListPage(driver, wait);
////        myWishListPage.test6();
//
//       double updatedQ=myWishListPage.changeQuantity();
//       double cartSum= myWishListPage.checkTheGrandPrice();
//       double grandPrice= myWishListPage.getGrandPrice();
//       Assert.assertEquals(updatedQ, 2, "Grand price are not equal.");
//       Assert.assertEquals(cartSum, grandPrice, "Grand price are not equal.");
//    }


@Test
public void testAddItemToCart() {
    logIn();
    driver.navigate().to("https://ecommerce.tealiumdemo.com/wishlist/");
    int index = 1;
    WishListPage cartPage = new WishListPage(driver,wait);
    ProductConfigPage productConfigPage=new ProductConfigPage(driver);
    cartPage.clickAddToCartForItem(index);

    wait.until(ExpectedConditions.urlContains("configure"));

    productConfigPage.selectColor();
    productConfigPage.selectSize();
    productConfigPage.addToCart();
}


    //T8
    //    @Test(dependsOnMethods = {"shoppingCart"})
//    @Test
//    public void emptyShoppingCart(){
//        logIn();
//        driver.navigate().to("https://ecommerce.tealiumdemo.com/checkout/cart/");
//        CartPage cartPage = new CartPage(driver);
//        cartPage.getProductItems();
//        System.out.println(cartPage.getProductItems().size());
//
//        cartPage.clickBin();
//        System.out.println(cartPage.getProductItems().size());
//
//        cartPage.elementsInCart();
//        System.out.println(cartPage.elementsInCart());
//    }
//

}














//@Test(dependsOnMethods = {"checkSortedByPriceAnd2WishListSelected"})
//public void ShoppingCart(){
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    WebElement optinCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("privacy_pref_optin")));
//    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", optinCheckbox);
//    wait.until(ExpectedConditions.elementToBeClickable(optinCheckbox)).click();
//
//    MyWishListPage myWishListPage = new MyWishListPage(driver, wait);
//    myWishListPage.goToMyWishList();

//        if(!driver.getCurrentUrl().contains("wishlist")){
//            CookieConsentPage cookiePage = new CookieConsentPage();
//            cookiePage.acceptCookies(wait);
//        }
//        myWishListPage.test6();
//}
