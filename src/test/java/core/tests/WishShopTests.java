package core.tests;

import core.pages.dashboard.DashboardPage;
import core.pages.menu.WomenPage;
import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WishShopTests extends TestCase {

//    //T6
//    @Test
//    public void checkSortedByPriceAnd2WilshlistSelected(){
//        logIn();
//        DashboardPage dashboardPage = new DashboardPage(driver, wait);
//        dashboardPage.goToWomenViewAll();
//
//
//        List<Double> prices = dashboardPage.getProductItems().stream()
//                .map(WomenPage::getPrice)
//                .map(p -> p.replace("$", "").trim())
//                .map(Double::parseDouble)
//                .collect(Collectors.toList());
//
//        System.out.println("Unsorted prices: " + prices);
//
//        dashboardPage.clickSortByDropDown();
//
//        List<Double> pricesR = dashboardPage.getProductItems().stream()
//                .map(WomenPage::getPrice)
//                .map(p -> p.replace("$", "").trim())
//                .map(Double::parseDouble)
//                .collect(Collectors.toList());
//
//        List<Double> reg=dashboardPage.getSortedProduct();
//
//        Assert.assertEquals(pricesR, reg, "Prices are not sorted in ascending order.");
//
////        dashboardPage.addElementToWishList(0);
////        driver.navigate().back();
//        dashboardPage.getProductItems();
//        dashboardPage.addElementToWishList(1);
//        dashboardPage.check();
//    }
//
//    //T7
//    @Test(dependsOnMethods = {"checkSortedByPriceAnd2WilshlistSelected"})
//    public void ShoppingCart(){
//
//    }
}
