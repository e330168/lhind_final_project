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
    public void testProductsList() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToWomenViewAll();

        List<WomenPage> items = dashboardPage.getProductItems();
        Assert.assertFalse(items.isEmpty(), "Product list should not be empty.");

        for (WomenPage item : items) {
            String name = item.getName();
            String price = item.getPrice();

            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Image: " + item.image.getAttribute("src"));

            Assert.assertNotNull(name, "Product name should not be null.");
        }
    }

    @Test
    public void hoverActionUI() {
        logIn();
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.goToWomenViewAll();

        WomenPage firstWomenProduct = dashboardPage.getProductItems().get(0);
        System.out.println(firstWomenProduct.getName());
        //        .no-touch .product-image:hover {
        //            border-color: #e26703;
        //        }
        String styleBefore = firstWomenProduct.hoverStyle.getCssValue("border-color");

        //        rgb(237, 237, 237)
        //        #ededed
        System.out.println("Before hover border-color: " + styleBefore);

        UIActions.hoverOver(firstWomenProduct.image);

        //      rgb(51, 153, 204)
        //      #3399cc
        String styleAfter = firstWomenProduct.hoverStyle.getCssValue("border-color");
        System.out.println("After hover border-color: " + styleAfter);
        Assert.assertNotEquals(styleBefore, styleAfter, "Border color should change on hover.");
    }
}