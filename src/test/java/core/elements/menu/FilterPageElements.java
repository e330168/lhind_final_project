package core.elements.menu;

import core.utils.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FilterPageElements {
    public FilterPageElements() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }

    @FindBy(xpath="//a[contains(@href, 'color=20')]")
    public WebElement blackColor;

    @FindBy(xpath="//a[contains(@href, 'color')]")
    public List<WebElement> listOfColors;

    @FindBy(xpath="//dl[@id='narrow-by-list']//dd[2]//a")
    public List<WebElement> listOfFilterPrice;

    @FindBy(xpath="//a[contains(@href,'-100')]")
    public WebElement priceFilter09;

    @FindBy(xpath="(//div[@class='count-container'])[1]//strong")
    public WebElement getSizeDisplayed;

}
