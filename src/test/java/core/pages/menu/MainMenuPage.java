package core.pages.menu;

import core.elements.navigation.AccountMenuElements;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final AccountMenuElements mainMenuElements;

    public MainMenuPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        mainMenuElements = new AccountMenuElements();
    }

    public void goToRegister() {
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenuElements.registerSubMenu);
        UIActions.click(driver, mainMenuElements.registerSubMenu);
    }

    public void goToLogin() {
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenuElements.logInSubMenu);
        UIActions.click(driver, mainMenuElements.logInSubMenu);
    }

    public void logout() {
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenuElements.logOutSubMenu);
        UIActions.click(driver, mainMenuElements.logOutSubMenu);
    }

    public void goToAccount(){
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);
    }

    public WebElement wishList(){
        return mainMenuElements.myWishListSubMenu;
    }

    public void goToShoppingCart() {
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenuElements.cartSubMenu);
        UIActions.click(driver, mainMenuElements.cartSubMenu);
    }

    public void goToMyWishList() {
        WaitUtils.waitForVisible(driver, mainMenuElements.accountMenu);
        UIActions.click(driver, mainMenuElements.accountMenu);

        WaitUtils.waitForVisible(driver, mainMenuElements.myWishListSubMenu);
        UIActions.click(driver, mainMenuElements.myWishListSubMenu);
    }

}