package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.elements.navigation.ShopByFilterElements;
import core.pages.components.ProductItem;
import core.pages.navigation.MainMenuPage;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WomenPage extends BasePageObject {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final ShopByFilterElements filterPageElements;
    private final DashboardPageElements dashboard;

    public WomenPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.filterPageElements = new ShopByFilterElements();
        this.dashboard = new DashboardPageElements();
    }

    public List<ProductItem> getProductItems() {
        List<ProductItem> items = new ArrayList<>();
        for (WebElement product : dashboard.productItems) {
            items.add(new ProductItem(product));
        }
        System.out.println("Total products: " + items.size());
        System.out.println("Product details: ");
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

    public List<Double> getSortedPricesAfterFilter(){
        List<Double> prices =getProductItems().stream()
                .map(ProductItem::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Unsorted pricesF: " + prices);

        clickSortByDropDown();

        List<Double> pricesR=getProductItems().stream()
                .map(ProductItem::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Sorted pricesF: " + pricesR);
        return pricesR;
    }

    public void clickSortByDropDown() {
        WaitUtils.waitForVisible(driver, filterPageElements.sortByDropDown);
        UIActions.click(driver, filterPageElements.sortByDropDown);

        WebElement sortDropDownE = filterPageElements.sortByDropDown;
        Select sortDropDown = new Select(sortDropDownE);
        List<WebElement> allOptions = sortDropDown.getOptions();

        WebElement optionPrice = null;
        for (WebElement option : allOptions) {
            if (option.getText().trim().equalsIgnoreCase("Price")) {
                optionPrice = option;
                break;
            }
        }

        System.out.println("Sorted by: " + optionPrice.getText());
        WaitUtils.waitForVisible(driver, sortDropDownE);
        UIActions.click(driver, optionPrice);
    }

    public List<Double> sortedPriceCollection(){
        List<Double> prices =getProductItems().stream()
                .map(ProductItem::getPrice)
                .map(p -> p.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Sorted pricesC: " + prices);
        return prices;
    }

    public void addToWishList(int productIndex) {
        List<ProductItem> products = new ArrayList<>();
        for (WebElement product : dashboard.productItems) {
            products.add(new ProductItem(product));
        }
        WebElement wishListButton = products.get(productIndex).getWishListButton();

        System.out.println("Name " + products.get(productIndex).getName());
        UIActions.click(driver, wishListButton);
        System.out.println("Link " +wishListButton.getAttribute("data-url"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='my-wishlist']//h1"))
        );

        System.out.println("Product added to wishlist"+ successMsg.getText());
    }

    public int nrItemsWishListSubMenu(){
        wait.until(ExpectedConditions.urlContains("wishlist"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-target-element='#header-account']")));
        MainMenuPage mainMenuPage = new MainMenuPage(driver, wait);
        mainMenuPage.goToAccount();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='links']//li[2]//a")));
        String wishlistText = mainMenuPage.wishList().getAttribute("title");
        System.out.println("Wishlist Text: " + wishlistText);

        int itemCount = 0;

        Pattern pattern = Pattern.compile("\\((\\d+) item");
        Matcher matcher = pattern.matcher(wishlistText);

        if (matcher.find()) {
            itemCount = Integer.parseInt(matcher.group(1));
        }
        System.out.println("Number of items in wishlist: " + itemCount);
        return itemCount;
    }

}