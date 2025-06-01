package testng.parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class MultipleBrowser {
    String browserName;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        initBrowser(browserName);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Navigate_Function() {
        visit("https://live.techpanda.org/index.php/customer/account/login/");
        sendKey(By.id("email"), "selenium_11_01@gmail.com");
        sendKey(By.id("pass"), "111111");
        click(By.name("send"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
