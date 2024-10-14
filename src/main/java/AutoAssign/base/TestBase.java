package AutoAssign.base;

import AutoAssign.extentReport.ExtentTestFactory;
import AutoAssign.extentReport.ExtentTestFactoryParent;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase
{
    public File WORKING_DIRECTORY;
    public static ExtentTestFactory extentTestFactory;
    private static ExtentReports extent;
    public static String filePath = System.getProperty("user.dir") + "/Reports/" + extentTestFactory.getPath();
    public static WebDriver driver;
    public static int WAIT_TIME=20;

    public void webSetUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/webDrivers/BrowserWebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        Thread.sleep(12000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(WAIT_TIME, TimeUnit.SECONDS);
    }

    public void loadExtentFile() {
        new File(filePath);
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
    }

    /**
     * Configure the extent test
     */
    public void configExtentTest(String className) {
        ExtentTest parent = extent.createTest(className);
        ExtentTestFactoryParent.setExtentTest(parent);
    }

    /**
     * @return ExtentHtmlReporter Instance
     */
    private static ExtentSparkReporter getHtmlReporter() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setDocumentTitle("Automation Assignment");
        htmlReporter.config().setReportName("Automation Assignment");
        htmlReporter.config().setTheme(Theme.DARK);
        return htmlReporter;
    }

    /**
     * Generate reports folder
     */
    public void makeReportsFolder() {
        WORKING_DIRECTORY = new File("Reports");
    }

    /**
     * Flush the report
     */
    public void flushReport() {
        extent.flush();
        System.out.println("Extent Reports is available here =>> " + filePath);
        System.out.println("After Suite Executed");
    }
    public void startTest(Method name) {
        ExtentTest child;
        child = ExtentTestFactoryParent.getExtentTest()
                .createNode(name.getName());
        ExtentTestFactory.setExtentTest(child);
    }
}
