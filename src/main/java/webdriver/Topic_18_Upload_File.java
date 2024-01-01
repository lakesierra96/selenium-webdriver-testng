package webdriver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_18_Upload_File {
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    String firstImg = "FirstIMG.JPG";
    String secondImg = "SecondIMG.jpg";

    String firstImgPath = projectPath + File.separator + "uploadFiles" + File.separator + firstImg;
    String secondImgPath = projectPath + File.separator + "uploadFiles" + File.separator + secondImg;

    @BeforeClass
    public void beforeClass() {
        //Disable chrome notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_One_File_Per_Time() {
        visit("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");
        driver.findElement(uploadFile).sendKeys(firstImgPath);
        driver.findElement(uploadFile).sendKeys(secondImgPath);

        Assert.assertTrue(isDisplayed(By.xpath("//p[@class='name' and text()='" + firstImg + "']")));
        Assert.assertTrue(isDisplayed(By.xpath("//p[@class='name' and text()='" + secondImg + "']")));

        List<WebElement> startButtons = getElements(By.cssSelector("td button.start"));
        for (WebElement start : startButtons) {
            start.click();
            sleepInSecond(2);
        }

        Assert.assertTrue(isDisplayed(By.xpath("//a[@title='" + firstImg + "']")));
        Assert.assertTrue(isDisplayed(By.xpath("//a[@title='" + secondImg + "']")));

        //Verify cac hinh nay upload len la hinh thuc bang js
        Assert.assertTrue(isImageLoaded("//a[@title='" + firstImg + "']/img"));
        Assert.assertTrue(isImageLoaded("//a[@title='" + secondImg + "']/img"));
    }

    @Test
    public void TC_02_Multiple_Files_Per_Time() {
        visit("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");
        driver.findElement(uploadFile).sendKeys(firstImgPath + "\n" + secondImgPath);
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public int getRandomNumber() {
        return new Random().nextInt(99999);
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
