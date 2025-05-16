package core.pages.menu;

import core.elements.dashboard.DashboardPageElements;
import core.elements.menu.WomenPageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WomenPage extends BasePageObject {
    private WomenPageElements womenPageElements;
    private WebDriver driver;
    private WebDriverWait wait;

    public WomenPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.womenPageElements = new WomenPageElements(productElement);
    }

    public WomenPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public String getName() {
        return womenPageElements.productName.getText();
    }

    public String getPrice() {
        return womenPageElements.price.getText();
    }

    public WebElement getImage() {
        return womenPageElements.image;
    }

    public String getImageSrc() {
        return womenPageElements.image.getAttribute("src");
    }

    public WebElement getHoverStyle() {
        return womenPageElements.hoverStyle;
    }

    public String getBorderOfSelectedColor(){
        return womenPageElements.selectedColor.getCssValue("border-color");
    }
}
