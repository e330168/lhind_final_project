package core.pages.navigation;

import core.elements.navigation.NavBarElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBarMenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final NavBarElements navBarElements;

    public NavBarMenuPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        navBarElements = new NavBarElements();
    }

    public void goToView(WebElement mainMenu, WebElement subMenu) {
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(subMenu)).click();
    }

    public void goToMenViewAll() {
        goToView(navBarElements.menMenu, navBarElements.menSubMenu);
    }

    public void goToWomenViewAll() {
        goToView(navBarElements.womenMenu, navBarElements.womenSubMenu);
    }

    public void goToSaleViewAll() {
        goToView(navBarElements.saleMenu, navBarElements.saleSubMenu);
    }
}
