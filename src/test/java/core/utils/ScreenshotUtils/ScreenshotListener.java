package core.utils.screenshotUtils;

import core.utils.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverProvider.getDriver();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }
}
