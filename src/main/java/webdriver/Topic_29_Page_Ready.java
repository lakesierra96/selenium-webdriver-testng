package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static support.Utils.*;

import java.util.concurrent.TimeUnit;

public class Topic_29_Page_Ready {
        WebDriver driver;
        WebDriverWait wait;


        @BeforeClass
        public void beforeClass() {
            openBrowser();
            wait = new WebDriverWait(driver, 30);
        }

        @Test
        public void TC_01_Orange_HRM_API() {
            visit("https://api.orangehrm.com/");

        }

        @AfterClass
        public void afterClass() {
//            driver.quit();
        }
    }