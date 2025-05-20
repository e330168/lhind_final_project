package core.utils.reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {
    private static ExtentReports extent;
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-report/alesia_mersalaTA.html");
            reporter.config().setReportName("Automation Report E-commerce Website");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            reporter.config().setTheme(Theme.STANDARD);
            extent.setSystemInfo("Tester", "Alesia Mersala");
        }
        return extent;
    }
}