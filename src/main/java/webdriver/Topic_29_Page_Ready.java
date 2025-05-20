package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        Assert.assertEquals("OrangeHRM REST API Documentation", getText(By.cssSelector("div#project h1")));
    }

    @Test
    public void TC_02_Admin_NopCommerce() {
        visit("https://admin-demo.nopcommerce.com");
        click(By.cssSelector("div.buttons"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
        click(By.xpath("//a[text()='Logout']"));
    }


    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
