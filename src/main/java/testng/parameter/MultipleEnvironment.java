package testng.parameter;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class MultipleEnvironment {

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

    @Parameters("environment")
    @Test(invocationCount = 3)
    public void TC_01_Login(String environmentName) {
        visit(getEnvironmentName(environmentName) + "index.php/customer/account/login/");
        sendKey(By.id("email"), "selenium_11_01@gmail.com");
        sendKey(By.id("pass"), "111111");
        click(By.name("send"));
        click(By.xpath("//header[@id='header']//span[text()='Account']"));
        click(By.linkText("Log Out"));
    }

    public String getEnvironmentName(String environmentName) {
        String url = null;
        if (environmentName.toLowerCase().equals("dev")) {
            url = "https://dev.techpanda.org/";
        } else if (environmentName.toLowerCase().equals("staging")) {
            url = "https://staging.techpanda.org/";
        } else if (environmentName.toLowerCase().equals("live")) {
            url = "https://live.techpanda.org/";
        } else {
            throw new RuntimeException("Invalid Url");
        }
        return url;
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
