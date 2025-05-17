package core.pages.menu;

import core.elements.dashboard.DashboardPageElements;
import core.elements.menu.FilterPageElements;
import core.elements.menu.WomenPageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WomenPage extends BasePageObject {
    private WomenPageElements womenPageElements;
    private FilterPageElements filterPageElements;
    private WebDriver driver;
    private WebDriverWait wait;

    public WomenPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.womenPageElements = new WomenPageElements(productElement);
        this.filterPageElements = new FilterPageElements();
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

    public String getOldPrice() {
        return womenPageElements.oldPrice.getText();
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

    public String getBorderOfSelectedColor() {
        return womenPageElements.selectedColor.getCssValue("border-color");
    }

    public WebElement getOriginalPriceStyle(){
        return womenPageElements.oldPrice;
    }

    public WebElement getFinalPrice() {
        return womenPageElements.price;
    }

    public WebElement getWishListButton() {return womenPageElements.addToWishlisLink;}

    public void clickOnWishlistL() {
        WaitUtils.waitForVisible(driver, womenPageElements.addToWishlisLink);
        UIActions.click(driver, womenPageElements.addToWishlisLink);
    }

    public boolean areShownMultiplePrices() {
        return !getOldPrice().isEmpty() && !getPrice().isEmpty();
    }

    public boolean checkOldPriceStyle() {
        String colStyle=getOriginalPriceStyle().getCssValue("color");
        String decStyle=getOriginalPriceStyle().getCssValue("text-decoration-line");

        return colStyle.equals("rgba(160, 160, 160, 1)") && decStyle.equals("line-through");
    }

    public boolean checkNewPriceStyle() {
        String colStyle=getFinalPrice().getCssValue("color");
        String decStyle=getFinalPrice().getCssValue("text-decoration-line");
        return colStyle.equals("rgba(51, 153, 204, 1)") && !decStyle.equals("line-through");
    }
}