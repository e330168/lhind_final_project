package core.elements.navigation;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopByFilterElements {
    public ShopByFilterElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//a[contains(@href, 'color=20')]")
    public WebElement blackColor;

    @FindBy(xpath="//a[contains(@href,'-100')]")
    public WebElement priceFilter09;

    @FindBy(xpath="(//div[@class='count-container'])[1]//strong")
    public WebElement getSizeDisplayed;

    @FindBy(xpath="//select[@title='Sort By']")
    public WebElement sortByDropDown;
}
