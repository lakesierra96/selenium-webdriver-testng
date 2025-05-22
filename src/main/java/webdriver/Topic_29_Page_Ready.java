package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;

import java.util.List;

import static support.Utils.*;

public class Topic_29_Page_Ready {
    WebDriverWait wait;
    Actions act;

    @BeforeClass
    public void beforeClass() {
        //openBrowser();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        act = new Actions(driver);
    }

    @Test
    public void TC_01_Orange_HRM_API() {
        visit("https://api.orangehrm.com/");
        Assert.assertTrue(isPageLoadedSuccess());
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        Assert.assertEquals(getText(By.cssSelector("div#project h1")), "OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_Admin_NopCommerce() {
        visit("https://admin-demo.nopcommerce.com");
        click(By.cssSelector("div.buttons"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
        click(By.xpath("//a[text()='Logout']"));
    }

    @Test
    public void TC_03_Blog_Test_Project() {
        //Web die roi nen xem lai topic 47
        visit("https://blog.testproject.io");

        //Di chuyển chuột vào 1 element bất kì thì page mới ready
        act.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();

        String keyword = "Selenium";
    }

    @Test
    public void TC_04_Tricentis() {
        visit("https://www.tricentis.com/search");

        sendKey(By.xpath("//div[@class=' dark-theme  ']//input[@placeholder='Search']"), "selenium");
        click(By.xpath("//div[@class=' dark-theme  ']//button[@type='submit']"));

        String keyword = "testing";

        //dùng contains khi value dài quá
        //List<WebElement> searchResults = getElements(By.xpath("//div[contains(@class,'SearchResultItem')]//h2"));
        List<WebElement> searchResults = getElements(By.xpath("h2"));

        for (WebElement item : searchResults) {
            Assert.assertTrue(item.getText().contains(keyword));
        }
    }


    @AfterClass
    public void tearDown() {
        //driver.quit();
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
