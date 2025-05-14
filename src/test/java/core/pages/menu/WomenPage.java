package core.pages.menu;

import core.pages.dashboard.DashboardPage;
import core.utils.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WomenPage extends BasePageObject {
    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".price")
    private WebElement price;

    @FindBy(xpath = "//*[contains(@id, 'product-collection-image')]")
    public WebElement image;

    @FindBy(css = ".product-image")
    public WebElement hoverStyle;

    private DashboardPage dashboardPage;
    private WebDriver driver;

    public WomenPage(WebDriver driver, WebElement productRoot) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(productRoot), this);
        this.dashboardPage = new DashboardPage(driver, wait);
    }

    public WomenPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getName() {
        return productName.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public WebElement getImage() {
        return image;
    }
}