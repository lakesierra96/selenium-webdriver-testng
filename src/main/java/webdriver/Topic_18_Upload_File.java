package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_18_Upload_File {
    WebDriverWait wait;
    String projectPath = System.getProperty("user.dir");

    String firstImg = "IMG_0580.JPG";
    String secondImg = "S__3547139.jpg";

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

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_One_File_Per_Time() {
        visit("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstImgPath);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(secondImgPath);
    }

    @Test
    public void TC_02_Multiple_Files_Per_Time() {

    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
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
