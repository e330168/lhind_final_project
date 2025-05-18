package core.elements.menu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;


public class WomenPageElements {
    public WomenPageElements(WebElement productRoot) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productRoot), this);
    }

    @FindBy(css = ".product-name")
    public WebElement productName;

    @FindBy(css="img[id*='collection']")
    public WebElement image;

    @FindBy(css = "[id*='product-p']")
    public WebElement price;

    //    .price-box .old-price .price {
    //        color: #a0a0a0;
    //        text-decoration: line-through;
    //    }
    @FindBy(css="[id*='old']")
    public WebElement oldPrice;

    @FindBy(css = ".product-image")
    public WebElement hoverStyle;

//    .configurable-swatch-list .selected .swatch-link,.swatch-link:hover {
//        border-color: #3399cc;
//    }

//    @FindBy(xpath = "//*[contains(@class, 'selected')]")
//    @FindBy(xpath = "//li[contains(@class, 'selected')]")
    @FindBy(xpath = "//a[contains(@name, 'black')]")
    public WebElement selectedColor;

    //a[@name = "black"]
    //*[contains(@class, 'has-image swatch-link')]

    @FindBy(css = "[class*='filter-match']")
    public WebElement filterMatch;

    @FindBy(css="//a[@name = 'black']")
    public WebElement black;

//    @FindBy(xpath = "//a[contains(@data-url, 'wish')]")
//    @FindBy(xpath = "//ul[@class='add-to-links']//li[1]//a")
    @FindBy(css=".link-wishlist")
    public WebElement addToWishlisLink;

    @FindBy(xpath = "(//*[contains(@class,'ratings')])/following-sibling::div/a")
    public WebElement viewDetailsButton;

}