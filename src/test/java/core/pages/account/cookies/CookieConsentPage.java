package core.pages.account.cookies;

import core.utils.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CookieConsentPage {
    private final By optInRadio = By.id("privacy_pref_optin");
    private final By submitButton = By.id("consent_prompt_submit");
    private final By prompt = By.className("privacy_prompt");
    private final By cookieAcceptButton = By.id("privacy_pref_optin");

    public void acceptCookies(WebDriverWait wait) {
        try {
            List<WebElement> cookieButtons = DriverProvider.getDriver().findElements(cookieAcceptButton);
            if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed() && cookieButtons.get(0).isEnabled()) {
                WebElement cookieButton = cookieButtons.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();

                WebElement optIn = wait.until(ExpectedConditions.elementToBeClickable(optInRadio));
                optIn.click();

                WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
                submit.click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(prompt));
            }
        } catch (Exception e) {
            System.out.println("Cookie not present or already handled: " + e.getMessage());
        }
    }
}
