package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import static support.Utils.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.events.Event;
import support.Utils;

import java.util.concurrent.TimeUnit;

public class Topic_03_XPath_Part1 {
        String projectPath = System.getProperty("user.dir");

        @BeforeClass
        public void beforeClass() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        }

        @Test
        public void TC_01_Register_Empty_Data() {
            getElement(By.id("txtFirstname")).sendKeys("");
            getElement(By.id("txtEmail")).sendKeys("");
            getElement(By.id("txtCEmail")).sendKeys("");
            getElement(By.id("txtPassword")).sendKeys("");
            getElement(By.id("txtCPassword")).sendKeys("");
            getElement(By.id("txtCPassword")).sendKeys("");
            getElement(By.id("txtPhone")).sendKeys("");
            click(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']"));

            Assert.assertEquals(getText(By.id("txtFirstname-error")), "Vui lòng nhập họ tên");
        }

        @Test
        public void TC_02_Register_Invalid_Email() {
            driver.navigate().refresh();
            getElement(By.id("txtFirstname")).sendKeys("");
            getElement(By.id("txtEmail")).sendKeys("##$%");
            getElement(By.id("txtCEmail")).sendKeys("");
            getElement(By.id("txtPassword")).sendKeys("");
            getElement(By.id("txtCPassword")).sendKeys("");
            getElement(By.id("txtCPassword")).sendKeys("");
            getElement(By.id("txtPhone")).sendKeys("");
            click(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']"));

            Assert.assertEquals(getText(By.id("txtEmail-error")), "Vui lòng nhập email hợp lệ");
        }

        @Test
        public void TC_03_Register_Invalid_Phone_Number() {
            // Login form displayed
        }

        @AfterClass
        public void afterClass() {
//            driver.quit();
        }
    }