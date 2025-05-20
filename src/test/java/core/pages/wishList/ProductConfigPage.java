package core.pages.wishList;

import core.elements.wishList.WishListConfigElements;
import core.utils.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductConfigPage extends BasePageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private WishListConfigElements wishListConfigElements;

    public ProductConfigPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wishListConfigElements=new WishListConfigElements();
    }

    public List<WebElement> getColors(){
        return wishListConfigElements.selectColor;
    }

    public List<WebElement> getSizes(){
        return wishListConfigElements.selectSize;
    }

    public WebElement addToCartButton(){
        return wishListConfigElements.addToCartButton;
    }

    public void selectColor() {
        List<WebElement> colorOptions = getColors();
        if (!colorOptions.isEmpty()) {
            WebElement firstColorLink = colorOptions.get(0).findElement(By.tagName("a"));
            System.out.println(firstColorLink.getText()+" firstColor");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstColorLink);
            firstColorLink.click();
        } else {
            System.out.println("No color options found.");
        }
    }

    public void selectSize() {
        List<WebElement> sizeOptions = getSizes();
        if (!sizeOptions.isEmpty()) {
            WebElement firstSizeLink = sizeOptions.get(0).findElement(By.tagName("a"));
            System.out.println(firstSizeLink.getText()+" firstSize");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstSizeLink);
            firstSizeLink.click();
        } else {
            System.out.println("No size options found.");
        }
    }

    public void addToCart() {
        WebElement button = addToCartButton();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted, using JS click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }
}