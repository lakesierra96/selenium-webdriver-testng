package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static support.Utils.*;

public class Topic_26_Wait_PV_Explicit_Wait_Excercise {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        openBrowser();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TC_01_Wait_For_Ajax_Loading() {
        visit("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        WebElement todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
        Assert.assertEquals(todayText.getText(), "No Selected Dates to display.");
        System.out.println(todayText.getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='9']"))).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='9']")));

        //sau khi select date thi element todayText bi thay doi trang thai, vi vay can khai bao lai de dc update thanh moi nhat neu ko se bi loi
        todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
        System.out.println(todayText.getText());
        Assert.assertEquals(todayText.getText(), "Friday, August 9, 2024");
    }


    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

        public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
