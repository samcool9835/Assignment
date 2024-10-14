package amazonTest;

import AutoAssign.base.TestSetup;
import AutoAssign.page.CartPage;
import AutoAssign.page.HomePage;
import AutoAssign.page.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ValidatePriceOnSearchResultsProductPageTotalSummary extends TestSetup
{
    @Test
    public void validatePriceOnSearchResultsProductPageTotalSummary() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        SearchPage sp = new SearchPage(driver);
        CartPage cp = new CartPage(driver);

        hp.clickOnSearchField();
        hp.enterItemToSearch("toys");

        //fetch the price from search page
        int firstProductPrice =sp.getProductsPrice(0);
        int secondProductPrice =sp.getProductsPrice(1);
        System.out.println("1st Product: "+firstProductPrice);
        System.out.println("2nd Product: "+secondProductPrice);

        //Select any two product
        sp.selectProducts(0);
        sp.selectProducts(1);

        //add on the cart
        sp.clickOnCart();

        //fetch the price from cart page
        int firstProductCartPrice =cp.getProductPrice(0);
        int secondProductCartPrice =cp.getProductPrice(1);
        int totalProductPrice = firstProductCartPrice+secondProductCartPrice;

        int totalPrice = cp.getTotalPrice();

        //validate the product price and total price
        Assert.assertEquals(firstProductPrice,firstProductCartPrice);
        Assert.assertEquals(secondProductPrice,secondProductCartPrice);
        Assert.assertEquals(totalProductPrice,totalPrice);

    }
    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }

}
