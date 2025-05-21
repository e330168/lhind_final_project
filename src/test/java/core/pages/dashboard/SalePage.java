package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.pages.components.ProductItem;
import core.utils.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SalePage extends BasePageObject {
    private final DashboardPageElements dashboardElements;
    WebDriver driver;
    WebDriverWait wait;

    public SalePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.dashboardElements = new DashboardPageElements();
    }

    public List<ProductItem> getProductItems() {
        List<ProductItem> items = new ArrayList<>();
        for (WebElement product : dashboardElements.productItems) {
            items.add(new ProductItem(product));
        }
        System.out.println("Total products: " + items.size());
        System.out.println("Product details: ");
        System.out.println(" ");
        items.forEach(item -> {
            String name = item.getName();
            String price = item.getPrice();
            String oldPrice = item.getOldPrice();
            boolean amountPrices= item.areShownMultiplePrices();
            String oldC=item.getOriginalPriceStyle().getCssValue("color");
            String oldD=item.getOriginalPriceStyle().getCssValue("text-decoration-line");
            String newC=item.getFinalPrice().getCssValue("color");
            String newD=item.getFinalPrice().getCssValue("text-decoration-line");


            System.out.println("Name: " + name);
            System.out.println("Multiple prices shown: " + amountPrices);
            System.out.println(" ");
            System.out.println("Old: ");
            System.out.println("Old Price: " + oldPrice);
            System.out.println("Old price color: " + oldC);
            System.out.println("Old decorator style: " + oldD);
            System.out.println(" ");
            System.out.println("New: ");
            System.out.println("New Price: " + price);
            System.out.println("New price color: " + newC);
            System.out.println("New decorator style: " + newD);
            System.out.println(" ");
        });
        return items;
    }

   public void checkMultiplePrices() {
       List<ProductItem> items = new ArrayList<>();
       for (WebElement product : dashboardElements.productItems) {
           items.add(new ProductItem(product));
       }
       items.forEach(ProductItem::areShownMultiplePrices);
   }
}
