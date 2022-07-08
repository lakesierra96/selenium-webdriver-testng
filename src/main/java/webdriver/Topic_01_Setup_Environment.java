package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_01_Setup_Environment {
        WebDriver driver;
        String projectPath = System.getProperty("user.dir");

        @BeforeClass
        public void beforeClass() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://www.facebook.com/");
        }

        @Test
        public void TC_01_ValidateCurrentUrl() {
            // Login Page Url matching
            String loginPageUrl = driver.getCurrentUrl();
            Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");
        }

        @Test
        public void TC_02_ValidatePageTitle() {
            // Login Page title
            String loginPageTitle = driver.getTitle();
            Assert.assertEquals(loginPageTitle, "Facebook – log in or sign up");
        }

        @Test
        public void TC_03_LoginFormDisplayed() {
            // Login form displayed
            Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
        }

        @AfterClass
        public void afterClass() {
//            driver.quit();
        }
    }