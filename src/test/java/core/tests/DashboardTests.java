package core.tests;

import core.pages.dashboard.DashboardPage;
import core.pages.menu.WomenPage;
import core.utils.TestBase;
import core.utils.UIActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test
    public void testMenProducts() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToMenViewAll();
        dashboardPage.selectColor();

        List<WomenPage> products = dashboardPage.getProductItemsMen();
        Assert.assertEquals(products.size(), 3);
        products.forEach(product -> {
            Assert.assertEquals(product.getBorderOfSelectedColor(), "rgb(51, 153, 204)", "Border of selected color should be rgb(51, 153, 204)");
        });

        driver.navigate().back();
        dashboardPage.clickOnPriceFilter();

        int priceFilterCount = dashboardPage.priceFilterCount();
        int totalProducts = dashboardPage.getProductItems().size();

        Assert.assertEquals(totalProducts, 3, "Total products displayed count should be 3");
        Assert.assertEquals(totalProducts, priceFilterCount, "Total products displayed count should be 3");
    }
}