package core.pages.dashboard;

import core.elements.dashboard.ProductElements;
import core.elements.navigation.ShopByFilterElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductItemPage extends BasePageObject {
    private ProductElements ProductElements;
    private ShopByFilterElements filterPageElements;
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductItemPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.ProductElements = new ProductElements(productElement);
        this.filterPageElements = new ShopByFilterElements();
    }

    public ProductItemPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public String getName() {
        return ProductElements.productName.getText();
    }

    public String getPrice() {
        return ProductElements.price.getText();
    }

    public String getOldPrice() {
        return ProductElements.oldPrice.getText();
    }

    public WebElement getImage() {
        return ProductElements.image;
    }

    public String getImageSrc() {
        return ProductElements.image.getAttribute("src");
    }

    public WebElement getHoverStyle() {
        return ProductElements.hoverStyle;
    }

    public String getBorderOfSelectedColor() {
        return ProductElements.selectedColor.getCssValue("border-color");
    }

    public WebElement getOriginalPriceStyle(){
        return ProductElements.oldPrice;
    }

    public WebElement getFinalPrice() {
        return ProductElements.price;
    }

    public WebElement getWishListButton() {return ProductElements.addToWishListLink;}

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