package core.elements.cart;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartElements {

    public CartElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath ="//table[@id='shopping-cart-table']//tbody//tr")
    public List<WebElement> productsOnCart;

    @FindBy(xpath=".//td[@class='a-center product-cart-remove last']//a")
    public WebElement deleteBin;

    @FindBy(xpath="//span[@class='count']")
    public WebElement nrOfCartProd;

    @FindBy(xpath="//tfoot//span[@class='price']")
    public WebElement grandPrice;
}
