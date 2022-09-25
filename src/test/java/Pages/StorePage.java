package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StorePage {
    private WebDriver driver;

    public StorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inventory_container")
    private WebElement invContainer;

    @FindBy(className = "inventory_item_img")
    private WebElement item;

//    @FindBy(className = "inventory_item_price")
//    private ArrayList<WebElement> prices;

    @FindBy(className = "product_sort_container")
    private WebElement sortingList;

    //this method aids in waiting for the store to load after logging in
    public WebElement waitForInventoryContainer() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement waitForInvContainer = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
        return waitForInvContainer;
    }

    public void clickOnProductToVisitDetails() {
        item.click();
    }

    public void sortPricesLowToHigh() {
        Select select = new Select(sortingList);
        select.selectByValue("lohi");
    }

    public void sortNamesAtoZ() {
        Select select = new Select(sortingList);
        select.selectByValue("az");
    }

    public boolean checkIfPricesSortedLowToHigh() {
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
//        String text = prices.get(0).getText().substring(1);
//        String text2 = prices.get(1).getText().substring(1);
//        System.out.println(text);
//        System.out.println(text2);

        for (int i = 0; i < prices.size(); i++) {
            if ((i + 1) < prices.size()) {
                if (Double.parseDouble(prices.get(i).getText().substring(1)) > Double.parseDouble(prices.get(i + 1).getText().substring(1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIfNamesSortedAtoZ() {
        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        ArrayList<String> productNames = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            productNames.add(names.get(i).getText());
            Iterator<String> it = productNames.iterator();
            String prev = it.next();
            while (it.hasNext()) {
                String next = it.next();
                if (prev.compareTo(next) > 0) {
                    return false;
                }
                prev = next;
            }
        }
        return true;
    }
}
