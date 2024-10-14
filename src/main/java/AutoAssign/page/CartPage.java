package AutoAssign.page;

import AutoAssign.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends Util
{
    public By totalPrice = By.xpath("//span[@class='a-color-price sc-price-container a-text-bold']");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public int getProductPrice(int Index)  {
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".a-price-whole"));
        String productPrice = productPrices.get(Index).getText().replaceAll("^[0-9]","");
        int productPriceText = Integer.parseInt(productPrice);
        return productPriceText;
    }

    public int getTotalPrice()
    {
        String productPriceText = waitForElement(totalPrice).getText().replaceAll("^[0-9]","");
        int productPrice = Integer.parseInt(productPriceText);
        return productPrice;
    }

}
