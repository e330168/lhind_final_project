package core.pages.menu;

import core.elements.menu.FilterPageElements;
import core.elements.menu.WomenPageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MenPage extends BasePageObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private FilterPageElements filterPageElements;
    private WomenPageElements womenPageElements;

    public MenPage(WebElement productElement) {
        super(DriverProvider.getDriver());
        this.womenPageElements = new WomenPageElements(productElement);
    }

    public MenPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
        filterPageElements = new FilterPageElements();
    }
}
