package core.elements.menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class SaleElements {
    public SaleElements(WebElement productRoot) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productRoot), this);
    }

    public SaleElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//ol[@class='nav-primary']/li/a[text()='Sale']")
    public WebElement saleMenu;

    @FindBy(xpath="//a[text()='View All Sale']")
    public WebElement saleSubMenu;
}
