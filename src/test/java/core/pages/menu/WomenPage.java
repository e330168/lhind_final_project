package core.pages.menu;

import core.elements.menu.WomenPageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;

public class WomenPage extends BasePageObject {
    private WomenPageElements womenPageElements;

    public WomenPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.womenPageElements = new WomenPageElements(productElement);
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
}