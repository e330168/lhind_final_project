package core.elements.wishList;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishListConfigElements {
    public WishListConfigElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath ="//li[contains(@class,'is-media')]")
    public List<WebElement> selectColor;

    @FindBy(xpath ="//ul[@id='configurable_swatch_size']//li[not(contains(@class, 'not-available'))]")
    public List<WebElement> selectSize;

    @FindBy(xpath = "//div[@class='add-to-cart-buttons']//button")
    public WebElement addToCartButton;

}
