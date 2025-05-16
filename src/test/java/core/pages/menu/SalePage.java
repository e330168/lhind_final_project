package core.pages.menu;

import core.elements.dashboard.DashboardPageElements;
import core.elements.menu.SalePageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SalePage extends BasePageObject {
    private SalePageElements salePageElements;
    private DashboardPageElements dashboardPageElements;
    WebDriver driver;
    WebDriverWait wait;

    public SalePage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.salePageElements = new SalePageElements(productElement);
    }

    public SalePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.salePageElements = new SalePageElements(driver);
        this.dashboardPageElements=new DashboardPageElements();
    }

    public void goToSaleViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(salePageElements.saleMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(salePageElements.saleSubMenu)).click();
    }

    public List<WomenPage> getProductItems() {
        List<WomenPage> items = new ArrayList<>();
        for (WebElement product : dashboardPageElements.productItems) {
            items.add(new WomenPage(product));
        }
        System.out.println("Total products: " + items.size());
        System.out.println("Product details: ");
        System.out.println(" ");
        items.forEach(item -> {
            String name = item.getName();
            String price = item.getPrice();
            String oldPrice = item.getOldPrice();
            Boolean amountPrices= item.areShownMultiplePrices();
            String oldC=item.getOriginalPriceStyle().getCssValue("color");
            String oldD=item.getOriginalPriceStyle().getCssValue("text-decoration-line");
            String newC=item.getFinalPrice().getCssValue("color");
            String newD=item.getFinalPrice().getCssValue("text-decoration-line");


            System.out.println("Name: " + name);
            System.out.println("Multiple prices shown: " + amountPrices);
            System.out.println(" ");
            System.out.println("New: ");
            System.out.println("New Price: " + price);
            System.out.println("New price color: " + newC);
            System.out.println("New decorator style: " + newD);
            System.out.println(" ");
            System.out.println("Old: ");
            System.out.println("Old Price: " + oldPrice);
            System.out.println("Old price color: " + oldC);
            System.out.println("Old decorator style: " + oldD);

            System.out.println(" ");
        });
        return items;
    }

   public List<WomenPage> checkMultiplePrices() {
       List<WomenPage> items = new ArrayList<>();
       for (WebElement product : dashboardPageElements.productItems) {
           items.add(new WomenPage(product));
       }
       items.forEach(WomenPage::areShownMultiplePrices);
       return items;
   }
}
