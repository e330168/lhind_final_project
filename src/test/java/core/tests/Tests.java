package core.tests;

import core.pages.cart.CartPage;
import core.pages.dashboard.ProductsGridPage;
import core.pages.menu.MainMenuPage;
import core.pages.dashboard.SalePage;
import core.pages.components.ProductItem;
import core.pages.wishList.WishListPage;
import core.utils.reportUtils.ReportListenerUtils;
import core.utils.screenshotUtils.ScreenshotListener;
import core.utils.TestBase;
import core.utils.UIActions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners({
        ScreenshotListener.class,
        ReportListenerUtils.class
})
public class Tests extends TestBase {

    @Test
    public void testProductList() {
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();

        List<ProductItem> items = ProductsGridPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");

        int itemsCount = items.size();
        assertEquals(itemsCount, 11, "Total number of products in list should be 11.");
    }

    //T3
    @Test
    public void hoverWomenProduct() {
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();

        ProductItem womenProduct = ProductsGridPage.getProductItems().get(3);
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
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToMenViewAll();

        List<ProductItem> items = ProductsGridPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");
        assertEquals(items.size(), 12, "Total number of products in list should be 12.");
    }

    //T4
    @Test
    public void checkSaleProductsStyle() {
        SalePage salePage = new SalePage(driver, wait);
        salePage.goToSaleViewAll();
        salePage.getProductItems();
        salePage.checkMultiplePrices();

        List<ProductItem> products = salePage.getProductItems();
        assertEquals(products.size(), 4);

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
                    "Final price should have color rgba(51, 153, 204, 1) and no line-through decoration."
            );
        });
    }

    //T5
    @Test
    public void checkPageFilters() {
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToMenViewAll();
        ProductsGridPage.selectColor();

        List<ProductItem> products = ProductsGridPage.getProductItemsMen();
        assertEquals(products.size(), 3);
        products.forEach(product -> {
            assertEquals(product.getBorderOfSelectedColor(), "rgb(51, 153, 204)", "Border of selected color should be rgb(51, 153, 204).");
        });

        driver.navigate().back();
        ProductsGridPage.clickOnPriceFilter();

        int priceFilterCount = ProductsGridPage.priceFilterCount();
        int totalProducts = ProductsGridPage.getProductItems().size();

        assertEquals(totalProducts, 3, "Total products displayed count should be 3.");
        assertEquals(totalProducts, priceFilterCount, "Total products displayed count should be 3.");

        Assert.assertTrue(ProductsGridPage.areAllPricesInRangeAfterFiltering());
    }

    //T6
    @Test
    public void checkSortedByPriceAnd2WishListSelected() {
        ProductsGridPage ProductsGridPage = new ProductsGridPage(driver, wait);
        ProductsGridPage.goToWomenViewAll();

        List<Double>pricesR = ProductsGridPage.getSortedPricesAfterFilter();
        List<Double> reg = ProductsGridPage.sortedPriceCollection();

        assertEquals(pricesR, reg, "Prices are not sorted in ascending order.");

        ProductsGridPage.addToWishList(0);
        driver.navigate().back();
        ProductsGridPage.addToWishList(1);

        int nrWishListProd = ProductsGridPage.nrItemsWishListSubMenu();
        assertEquals(nrWishListProd, 2, "Wishlist count does not match expected.");
    }


    //T7
//    @Test
    @Test(dependsOnMethods = {"checkSortedByPriceAnd2WishListSelected"})
    public void shoppingCart() {
        WishListPage wishListPage = new WishListPage(driver, wait);
        MainMenuPage mainMenuPage = new MainMenuPage(driver, wait);
        CartPage cartPage = new CartPage(driver, wait);

        mainMenuPage.goToMyWishList();
        wishListPage.addToCart();
        double updatedQ = cartPage.verifyCartTotalAfterQuantityUpdate();
        assertEquals(updatedQ, 2, "Quantity is not updated to 2.");

        double actualTotal = cartPage.subTotalSum();
        double expectedTotal = cartPage.getGrandTotal();

        System.out.println("Subtotal Sum: $" + expectedTotal);
        System.out.println("Grand Total:  $" + actualTotal);

        assertEquals(actualTotal, expectedTotal, " Grand total doesn't match the sum of item subtotals.");
    }


    //T8
    @Test
//    @Test(dependsOnMethods = {"shoppingCart","checkSortedByPriceAnd2WishListSelected"})
    public void testDeleteAllItemsFromCart() {
        CartPage cartPage = new CartPage(driver, wait);
        MainMenuPage mainMenuPage = new MainMenuPage(driver, wait);
        mainMenuPage.goToShoppingCart();

        cartPage.getCartItems();
        int initialCount = cartPage.getCartItemsCount();

        for (int i = initialCount; i > 0; i--) {
            int beforeCount = cartPage.getCartItemsCount();
            cartPage.deleteFirstItemFromCart();
            int afterCount = cartPage.getCartItemsCount();

            assertEquals(afterCount, beforeCount - 1, "Cart product count should decrease by 1 after being deleted.");
        }
        assertEquals(cartPage.isCartEmptyMessageDisplayed(), true);
        assertEquals(cartPage.getCartItemsCount(), 0, "Cart should be empty after deleting all products.");
    }

}