package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static support.Utils.driver;

public class Topic_05_WebBrowser_Element_P2 {
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Is_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("mail")).sendKeys("Automation");
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed()) {
            driver.findElement(By.id("under_18")).click();
            System.out.println("Display");

        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.id("edu")).isDisplayed()) {
            driver.findElement(By.id("edu")).sendKeys("Automation");
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    @AfterClass
    public void afterClass() {
//            driver.quit();
    }
}