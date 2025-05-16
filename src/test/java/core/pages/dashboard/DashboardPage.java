package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.elements.menu.FilterPageElements;
import core.pages.menu.WomenPage;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePageObject {
    private  WebDriver driver;
    private  WebDriverWait wait;
    private  DashboardPageElements dashboardPageElements;

    private FilterPageElements filterPageElements;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.dashboardPageElements = new DashboardPageElements();
        this.filterPageElements = new FilterPageElements();
    }

    public String getWelcomeMessageText() {
        return dashboardPageElements.welcomeMessage.getText();
    }

    public String getSuccessRegisterMessage() {
        return dashboardPageElements.registerSuccessMessage.getText();
    }

    public void goToWomenViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dashboardPageElements.womenMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageElements.womenSubMenu)).click();
    }

    public List<WomenPage> getProductItems() {
        List<WomenPage> items = new ArrayList<>();
        for (WebElement product : dashboardPageElements.productItems) {
            items.add(new WomenPage(product));
        }
        System.out.println("Total products: " + items.size());
        System.out.println("Product details: :");
        System.out.println(" ");
        items.forEach(item -> {
            String name = item.getName();
            String price = item.getPrice();
            String image = item.getImageSrc();

            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Image: " + image);
            System.out.println(" ");
        });
        return items;
    }

    public void goToMenViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dashboardPageElements.menMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageElements.menSubMenu)).click();
    }

    public void selectColor() {
        WaitUtils.waitForVisible(driver, filterPageElements.blackColor);
        UIActions.click(driver, filterPageElements.blackColor);
    }

    public List<WomenPage> getProductItemsMen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='filter-match']")));
        List<WebElement> elements = dashboardPageElements.productItems;
        List<WomenPage> products = new ArrayList<>();
        for (WebElement el : elements) {
            products.add(new WomenPage(el));
        }
        System.out.println("Total products: " + products.size());
        System.out.println("Product details: ");
        System.out.println(" ");

        products.forEach(product -> {
            System.out.println("Selected border-color: "+product.getBorderOfSelectedColor());
            System.out.println("Name: "+  product.getName());
            System.out.println("Price: "+ product.getPrice());
            System.out.println("Image: "+ product.getImageSrc());
            System.out.println(" ");
        });

        return products;
    }

    public void clickOnPriceFilter() {
        WaitUtils.waitForVisible(driver, filterPageElements.priceFilter09);
        UIActions.click(driver, filterPageElements.priceFilter09);
    }

    public int priceFilterCount(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='count-container'])[1]//strong")));
        String items = filterPageElements.getSizeDisplayed.getText().split(" ")[0];
        return Integer.parseInt(items);
    }
}