package core.elements.dashboard;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPageElements {
    public DashboardPageElements(){
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//p[contains(text(),'Welcome')]")
    public WebElement welcomeMessage;

    @FindBy(xpath="//span[contains(text(), 'Thank')]")
    public WebElement registerSuccessMessage;

    @FindBy(xpath="//div[@class='cart-empty']//p")
    public WebElement emptyCartMessage;

    @FindBy(xpath = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li")
    public List<WebElement> productItems;

}
