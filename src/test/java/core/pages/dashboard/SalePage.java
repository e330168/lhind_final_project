package core.pages.dashboard;

import core.elements.dashboard.ProductsElements;
import core.utils.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SalePage extends BasePageObject {
    private ProductsElements productsGridPageElements;
    WebDriver driver;
    WebDriverWait wait;

    public SalePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.productsGridPageElements = new ProductsElements();
    }

    public void goToSaleViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(productsGridPageElements.saleMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(productsGridPageElements.saleSubMenu)).click();
    }

    public List<ProductItemPage> getProductItems() {
        List<ProductItemPage> items = new ArrayList<>();
        for (WebElement product : productsGridPageElements.productItems) {
            items.add(new ProductItemPage(product));
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

   public void checkMultiplePrices() {
       List<ProductItemPage> items = new ArrayList<>();
       for (WebElement product : productsGridPageElements.productItems) {
           items.add(new ProductItemPage(product));
       }
       items.forEach(ProductItemPage::areShownMultiplePrices);
   }
}
