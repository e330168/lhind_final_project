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

    //    @FindBy(xpath="//tr[@class='first last odd']//td[6]/a")
//    @FindBy(css=".btn-remove ,btn-remove2")
    @FindBy(xpath="//tbody//td[6]//a")
    public WebElement deleteBin;

    @FindBy(xpath="//span[@class='count']")
    public WebElement nrOfCartProd;
}
