package AutoAssign.base;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestSetup extends TestBase
{
    @BeforeSuite()
    public void beforeSuite() throws Exception {
        webSetUp();
        loadExtentFile();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        makeReportsFolder();
        System.out.println("=============================Before Test TestSetup==============================");
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("=============================Before Class TestSetup==============================");
        configExtentTest(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1));
    }

    @BeforeMethod(alwaysRun = true) //Initiate this if you have to launch the appium once for all set of tests
    public void beforeMethod(Method name) {
        System.out.println("==================================Before Method TestSetup=======================");
        startTest(name);
    }

    @AfterMethod(alwaysRun = true) //Initiate those if you have to launch the appium once for all set of tests
    public void afterMethod(ITestResult result) throws IOException {
        System.out.println("==================================After Method TestSetup=======================");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("==================================After Class TestSetup=======================");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("==================================After Suite TestSetup=======================");
        flushReport();
    }
}
