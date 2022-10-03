package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_05_WebBrowser_Element_P3 {
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Is_Displayed() {
        visit("https://opensource-demo.orangehrmlive.com");
        waitForElementPresent(By.xpath("//p[contains(text(),'Username')]"));
        sleepInSecond(1);
        String userField = driver.findElement(By.xpath("//p[contains(text(),'Username')]")).getText();
        System.out.println(userField);
    }

    @Test
    public void TC_02_MailChimp() {

    }

    @AfterClass
    public void afterClass() {
            driver.quit();
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}