package testng.parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class MultipleBrowser {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
       /* if (browserName.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browserName.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browserName.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else{
            throw new RuntimeException("Invalid browser");
        }*/
        initBrowser(browserName);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Navigate_Function() {
        visit("https://live.techpanda.org/index.php/customer/account/login/");
        sendKey(By.id("email"), "selenium_11_01@gmail.com");
        sendKey(By.id("pass"), "111111");
        click(By.id("send2"));
        Assert.assertTrue(getText(By.xpath("//div[@class='col-1']//p")).contains("selenium_11_01@gmail.com"));
        click(By.xpath("//header[@id='header']//span[text()='Account']"));
        click(By.linkText("Log Out"));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
       // driver.quit();
    }
}
