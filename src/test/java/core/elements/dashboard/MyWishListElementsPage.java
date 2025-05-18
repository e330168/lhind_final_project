package core.elements.dashboard;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyWishListElementsPage{
    public MyWishListElementsPage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//div[@class='cart-cell'][1]//button")
//    @FindBy(css=".button btn-cart")
//    @FindBy(css=".button.btn-cart")
    public WebElement addToCartButton;

    @FindBy(xpath ="//ul[@id='configurable_swatch_color']//li")
//    @FindBy(xpath ="//ul[@id='configurable_swatch_color']//a")
    public List<WebElement> selectColor;


//    @FindBy(xpath ="//li[@id='option20']")
//    public WebElement selectColor;

    @FindBy(xpath ="//ul[@id='configurable_swatch_size']//li")
//    @FindBy(xpath ="//ul[@id='configurable_swatch_size']//li//a")
    public List<WebElement> selectSize;
    ;

////    @FindBy(xpath ="//li[@id='option81']")
//    @FindBy(xpath ="//a[@id='swatch79']")
//    public WebElement selectSize;

//    @FindBy(id = "#map-popup-button")
    @FindBy(xpath = "//button[@id='map-popup-button']")
    public WebElement addToCartBF;


    @FindBy(css=".input-text qty")
    public WebElement quantity;

    @FindBy(css=".button btn-update")
    public WebElement updateQuantity;

    @FindBy(xpath="//span[@class='count']")
    public WebElement nrOfCartProd;

//    @FindBy(xpath="//tr[@class='first last odd']//td[6]/a")
    @FindBy(css=".btn-remove ,btn-remove2")
    public WebElement deleteBin;

    @FindBy(xpath="//div[@class='cart-empty']")
    public WebElement emptyCartMessage;

}
