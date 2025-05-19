package core.elements.dashboard;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsElements {
    public ProductsElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//p[contains(text(),'Welcome')]")
    public WebElement welcomeMessage;

    @FindBy(xpath="//span[contains(text(), 'Thank')]")
    public WebElement registerSuccessMessage;

    @FindBy(xpath="//ol[@class='nav-primary']/li/a[text()='Women']")
    public WebElement womenMenu;

    @FindBy(xpath="//a[text()='View All Women']")
    public WebElement womenSubMenu;

    @FindBy(xpath = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li")
    public List<WebElement> productItems;

    @FindBy(xpath = "//ol[@class='nav-primary']/li/a[text()='Men']")
    public WebElement menMenu;

    @FindBy(xpath = "//a[text()='View All Men']")
    public WebElement menSubMenu;

    @FindBy(xpath="//ol[@class='nav-primary']/li/a[text()='Sale']")
    public WebElement saleMenu;

    @FindBy(xpath="//a[text()='View All Sale']")
    public WebElement saleSubMenu;
}
