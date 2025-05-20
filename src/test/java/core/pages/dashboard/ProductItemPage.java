package core.pages.dashboard;

import core.elements.dashboard.ProductItemElements;
import core.elements.navigation.ShopByFilterElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductItemPage extends BasePageObject {
    private ProductItemElements productItemElements;
    private ShopByFilterElements filterPageElements;
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductItemPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.productItemElements = new ProductItemElements(productElement);
        this.filterPageElements = new ShopByFilterElements();
    }

    public ProductItemPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public String getName() {
        return productItemElements.productName.getText();
    }

    public String getPrice() {
        return productItemElements.price.getText();
    }

    public String getOldPrice() {
        return productItemElements.oldPrice.getText();
    }

    public WebElement getImage() {
        return productItemElements.image;
    }

    public String getImageSrc() {
        return productItemElements.image.getAttribute("src");
    }

    public WebElement getHoverStyle() {
        return productItemElements.hoverStyle;
    }

    public String getBorderOfSelectedColor() {
        return productItemElements.selectedColor.getCssValue("border-color");
    }

    public WebElement getOriginalPriceStyle(){
        return productItemElements.oldPrice;
    }

    public WebElement getFinalPrice() {
        return productItemElements.price;
    }

    public WebElement getWishListButton() {return productItemElements.addToWishListLink;}

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