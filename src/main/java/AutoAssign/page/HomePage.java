package AutoAssign.page;

import AutoAssign.base.TestBase;
import AutoAssign.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Util
{

    public By searchField = By.xpath("//input[@id='twotabsearchtextbox']");
    public By searchButton = By.xpath("//input[@id='nav-search-submit-button']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnSearchField()
    {
        waitForClick(searchField);
        return this;
    }
    public HomePage enterItemToSearch(String item)
    {
        waitForElement(searchField).sendKeys(item);
        waitForClick(searchButton);
        return this;
    }

}
