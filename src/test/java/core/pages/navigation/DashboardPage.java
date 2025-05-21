package core.pages.navigation;

import core.elements.dashboard.DashboardPageElements;
import core.utils.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePageObject {
    private final DashboardPageElements dashboard;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
        this.dashboard = new DashboardPageElements();
    }

    public String getSuccessRegisterMessage() {
        return dashboard.registerSuccessMessage.getText();
    }

    public String getWelcomeMessageText() {
        return dashboard.welcomeMessage.getText();
    }

    public WebElement emptyCartMessage() {
        return dashboard.emptyCartMessage;
    }

}