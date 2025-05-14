package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.pages.menu.WomenPage;
import core.utils.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePageObject {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final DashboardPageElements dashboardPageElements;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.dashboardPageElements = new DashboardPageElements();
        PageFactory.initElements(driver, this.dashboardPageElements);
    }

    public String getWelcomeMessageText() {
        return dashboardPageElements.welcomeMessage.getText();
    }


    public List<WomenPage> getProductItems() {
        List<WomenPage> items = new ArrayList<>();
        for (WebElement product : dashboardPageElements.productItems) {
            items.add(new WomenPage(driver, product));
        }
        return items;
    }

    public List<WebElement> getProductItemElements() {
        return dashboardPageElements.productItems;
    }

    public WomenPage goToWomenViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dashboardPageElements.womenMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageElements.womenSubMenu)).click();
        return new WomenPage(driver, wait);
    }
}