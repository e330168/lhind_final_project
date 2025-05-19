package core.pages.wishlist_cart;

import core.utils.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductConfigPage extends BasePageObject {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductConfigPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectColor() {
        List<WebElement> colorOptions = driver.findElements(By.xpath("//li[contains(@class,'is-media')]"));

        if (!colorOptions.isEmpty()) {
            WebElement firstColorLink = colorOptions.get(0).findElement(By.tagName("a"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstColorLink);
            firstColorLink.click();
        } else {
            System.out.println("No color options found.");
        }
    }

    public void selectSize(){
        List<WebElement> optionElements = driver.findElements(By.xpath("//li[contains(@class,'option-')]"));
        if (!optionElements.isEmpty()) {
            WebElement firstOptionLink = optionElements.get(0).findElement(By.tagName("a"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstOptionLink);
            firstOptionLink.click();
        } else {
            System.out.println("No option elements found");
        }
    }

    public void addToCart() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='add-to-cart-buttons']//button")));
        addToCartBtn.click();
    }
}
