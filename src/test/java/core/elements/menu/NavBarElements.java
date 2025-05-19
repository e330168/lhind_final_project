package core.elements.menu;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBarElements {
    public NavBarElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
    @FindBy(xpath="//ol[@class='nav-primary']/li/a[text()='Women']")
    public WebElement womenMenu;

    @FindBy(xpath="//a[text()='View All Women']")
    public WebElement womenSubMenu;

    @FindBy(xpath = "//ol[@class='nav-primary']/li/a[text()='Men']")
    public WebElement menMenu;

    @FindBy(xpath = "//a[text()='View All Men']")
    public WebElement menSubMenu;
}
