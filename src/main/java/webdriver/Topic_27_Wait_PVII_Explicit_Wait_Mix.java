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

import java.io.File;

import static support.Utils.*;

public class Topic_27_Wait_PVII_Explicit_Wait_Mix {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    String projectPath = System.getProperty("user.dir");

    String firstImg = "FirstIMG.JPG";
    String secondImg = "SecondIMG.jpg";

    String firstImgPath = projectPath + File.separator + "uploadFiles" + File.separator + firstImg;
    String secondImgPath = projectPath + File.separator + "uploadFiles" + File.separator + secondImg;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        openBrowser();
        wait = new WebDriverWait(driver, 30);
    }

    //bai nay xem video Topic 45


    @Test
    public void TC_01_Wait_For_Ajax_Loading() {

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
