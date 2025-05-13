package core.pages.dashboard;

import core.elements.dashboard.DashboardPageElements;
import core.utils.BasePageObject;
import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePageObject {
    DashboardPageElements dashboardPageElements =  new DashboardPageElements();

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeMessageText() {
        return dashboardPageElements.welcomeMessage.getText();
    }
}
