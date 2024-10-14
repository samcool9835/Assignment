package AutoAssign.util;

import AutoAssign.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util extends TestBase
{
    public Util(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public Util waitForClick(By locator) {
        WebElement element = waitForElement(locator);
        element.click();
        return this;
    }

    public void waitForSeconds(int seconds) {
        try {
            System.out.println("Waiting for " + seconds + " Seconds");
            Thread.sleep(seconds * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
