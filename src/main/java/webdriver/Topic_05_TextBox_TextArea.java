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

public class Topic_05_TextBox_TextArea {
    String firstName = "Hugh";

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_TextBox_TextArea() {
        visit("https://opensource-demo.orangehrmlive.com");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        click(By.xpath("//a[text()='Add Employee']"));
        sendKey(By.name("firstName"), firstName);
        sendKey(By.name("lastName"), "Pham");
        String empID = getAttribute(By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//input[@class='oxd-input oxd-input--active']"), "value");
        System.out.println(empID);
        click(By.xpath("//button[@type='submit']"));

        Assert.assertTrue(driver.findElement(By.name("firstName")).isEnabled());
        Assert.assertEquals(getAttribute(By.name("firstName"), "value"), firstName);
        Assert.assertEquals(getAttribute(By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//input[@class='oxd-input oxd-input--active']"), "value"), empID);

        click(By.xpath("//a[text()='Immigration']"));
        click(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button"));
        sendKey(By.xpath("(//div[@class='oxd-form-row'][last()]//input[@class='oxd-input oxd-input--active'])[1]"), "1708");
        sendKey(By.xpath("//textarea"), "ABCXYZ");
        click(By.xpath("//button[@type='submit']"));
        waitForElementPresent(By.xpath("//div[@class='oxd-table-body']//label"));
        click(By.xpath("//div[@class='oxd-table-body']//label"));

    }

    @Test
    public void TC_02_MailChimp() {

    }

    @AfterClass
    public void afterClass() {
//            driver.quit();
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}