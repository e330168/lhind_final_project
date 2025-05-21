package core.pages.navigation;

import core.elements.navigation.AccountMenuElements;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final AccountMenuElements accountMenu;

    public MainMenuPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        accountMenu = new AccountMenuElements();
    }

    private void openAccountAndClick(WebElement subMenu) {
        WaitUtils.waitForVisible(driver, accountMenu.accountMenu);
        UIActions.click(driver, accountMenu.accountMenu);

        WaitUtils.waitForVisible(driver, subMenu);
        UIActions.click(driver, subMenu);
    }

    public void goToRegister() {
        openAccountAndClick(accountMenu.registerSubMenu);
    }

    public void goToLogin() {
        openAccountAndClick(accountMenu.logInSubMenu);
    }

    public void logout() {
        openAccountAndClick(accountMenu.logOutSubMenu);
    }

    public void goToShoppingCart() {
        openAccountAndClick(accountMenu.cartSubMenu);
    }

    public void goToMyWishList() {
        openAccountAndClick(accountMenu.myWishListSubMenu);
    }

    public void goToAccount() {
        WaitUtils.waitForVisible(driver, accountMenu.accountMenu);
        UIActions.click(driver, accountMenu.accountMenu);
    }

    public WebElement wishList() {
        return accountMenu.myWishListSubMenu;
    }

}