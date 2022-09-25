package Tests;

import Pages.LoginPage;
import Pages.ProductPage;
import Pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PositiveTests extends TestBase {
    @Test
    public void loginIntoAppTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.loginButton();
        StorePage storePage = new StorePage(driver);
        WebElement storePageLoaded = storePage.waitForInventoryContainer();
        assertTrue(storePageLoaded.isDisplayed());

    }

    @Test
    public void clickOnProductAndCheckIfItemPageLoads() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fullLogin("standard_user", "secret_sauce");
        StorePage storePage = new StorePage(driver);
        storePage.clickOnProductToVisitDetails();
        ProductPage productPage = new ProductPage(driver);
        WebElement webElement = productPage.productDiv();

        assertTrue(webElement.isDisplayed());
    }

    @Test
    public void checkIfSortingLoHiWorks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fullLogin("standard_user", "secret_sauce");
        StorePage storePage = new StorePage(driver);
        storePage.sortPricesLowToHigh();
        boolean pricesSorted = storePage.checkIfPricesSortedLowToHigh();
        assertTrue(pricesSorted);
    }

    @Test
    public void checkIfSortingAtoZWorks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fullLogin("standard_user", "secret_sauce");
        StorePage storePage = new StorePage(driver);
        storePage.sortNamesAtoZ();
        boolean namesSorted = storePage.checkIfNamesSortedAtoZ();
        assertTrue(namesSorted);
    }
}
