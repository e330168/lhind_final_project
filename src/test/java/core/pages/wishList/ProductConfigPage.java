package core.pages.wishList;

import core.elements.wishList.WishListConfigElements;
import core.utils.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductConfigPage extends BasePageObject {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WishListConfigElements wishListConfigElements;

    public ProductConfigPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
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

    public void selectFirstOption(List<WebElement> listOfOptions, String selectedOption) {
        if (listOfOptions != null && !listOfOptions.isEmpty()) {
            WebElement firstLink = listOfOptions.get(0).findElement(By.tagName("a"));
            System.out.println(firstLink.getText() + " first " + selectedOption);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstLink);
            firstLink.click();
        } else {
            System.out.println("No " + selectedOption + " options found.");
        }
    }

    public void selectColor() {
        selectFirstOption(getColors(), "color");
    }

    public void selectSize() {
        selectFirstOption(getSizes(), "size");
    }

    public void addToCart() {
        WebElement button = addToCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted, using JS click.");
        }
    }
}