package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.pages.menu.MenPage;
import core.pages.menu.WomenPage;
import core.utils.BasePageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePageObject {
    private  WebDriver driver;
    private  WebDriverWait wait;
    private  DashboardPageElements dashboardPageElements;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
        this.dashboardPageElements = new DashboardPageElements();
    }

    public String getWelcomeMessageText() {
        return dashboardPageElements.welcomeMessage.getText();
    }

    public String getSuccessRegisterMessage() {
        return dashboardPageElements.registerSuccessMessage.getText();
    }

    public void goToWomenViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dashboardPageElements.womenMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageElements.womenSubMenu)).click();
    }

    public List<WomenPage> getProductItems() {
        List<WomenPage> items = new ArrayList<>();
        for (WebElement product : dashboardPageElements.productItems) {
            items.add(new WomenPage(product));
        }
        return items;
    }

    public void goToMenViewAll() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dashboardPageElements.menMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageElements.menSubMenu)).click();
    }
}