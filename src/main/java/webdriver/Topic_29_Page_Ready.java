package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static support.Utils.*;

public class Topic_29_Page_Ready {
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        openBrowser();
        wait = new WebDriverWait(driver, 30);
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
        click(By.cssSelector("div.buttons"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
        click(By.xpath("//a[text()='Logout']"));
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
