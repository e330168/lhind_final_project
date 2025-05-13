package core.elements.dashboard;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageElements {
    public DashboardPageElements(){
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//p[contains(text(),'Welcome')] ")
    public WebElement welcomeMessage;
}
