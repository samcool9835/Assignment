package AutoAssign.page;


import AutoAssign.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends Util
{
    public By cart = By.xpath("//div[@id='nav-cart-text-container']//child::span[contains(text(),'Cart')]");
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void selectProducts(int index) {
        List<WebElement> addToCart = driver.findElements(By.xpath("//*[contains(text(),'Add to cart')]"));

        if (addToCart.size() >= 0) {
            addToCart.get(index).click();
            waitForSeconds(4);
        }
    }

    public int getProductsPrice(int Index) throws InterruptedException {
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".a-price-whole"));

        String productPriceText = productPrices.get(Index).getText().replaceAll("[^0-9]", "");

        // Convert the cleaned text to an integer
        int productPrice = Integer.parseInt(productPriceText);
        return productPrice;
    }

    public SearchPage clickOnCart()
    {
        waitForClick(cart);
        waitForSeconds(3);
        return this;
    }

}