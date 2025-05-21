package core.pages.account.cookies;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookieConsentPage {

    private final By optInRadio = By.id("privacy_pref_optin");
    private final By submitButton = By.id("consent_prompt_submit");
    private final By prompt = By.className("privacy_prompt");
    private final By cookieAcceptButton = By.id("privacy_pref_optin");

    public void acceptCookies(WebDriverWait wait) {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(cookieAcceptButton));
            if (cookieButton.isDisplayed() && cookieButton.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
                cookieButton.click();
                WebElement optIn = wait.until(ExpectedConditions.elementToBeClickable(optInRadio));
                optIn.click();
                WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
                submit.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(prompt));
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}