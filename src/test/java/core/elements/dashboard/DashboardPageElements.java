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

    @FindBy(xpath="//ol[@class='nav-primary']/li/a[text()='Women']")
    public WebElement womenMenu;

    @FindBy(xpath="//a[text()='View All Women']")
    public WebElement womenSubMenu;

    @FindBy(xpath = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li")
    public List<WebElement> productItems;

}
