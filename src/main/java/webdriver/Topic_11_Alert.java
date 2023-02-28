package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_11_Alert {
    Random random;
    WebDriverWait wait;
    JavascriptExecutor js;
    Alert alert;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        random = new Random();
        wait = new WebDriverWait(driver, 30);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        visit("https://automationfc.github.io/basic-form/index.html");
        click(By.xpath("//button[text()='Click for JS Alert']"));
        driver.switchTo().alert().accept();
        Assert.assertEquals(getText(By.id("result")), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Accept_Confirm() {
        visit("https://automationfc.github.io/basic-form/index.html");
        click(By.xpath("//button[text()='Click for JS Confirm']"));
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(getText(By.id("result")), "You clicked: Ok");

        click(By.xpath("//button[text()='Click for JS Confirm']"));
        alert.dismiss();
        Assert.assertEquals(getText(By.id("result")), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Input_Prompt() {
        String jsPrompt = "Test";
        visit("https://automationfc.github.io/basic-form/index.html");
        click(By.xpath("//button[text()='Click for JS Prompt']"));
        sleepInSecond(3);

        //wait and switch then
        alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys(jsPrompt);
        alert.accept();
        Assert.assertEquals(getText(By.id("result")), "You entered: " + jsPrompt);
    }

    @Test
    public void TC_04_Authentication_Alert() {
        /*//C1
        visit("http://admin:admin@the-internet.herokuapp.com/basic_auth");*/

        visit(byPassAuthentication("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));

        Assert.assertTrue(isDisplayed(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")));
    }

    //C2
    public String byPassAuthentication(String url, String username, String password) {
        String[] arrayUrl = url.split("//");
        return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public boolean isElementSelected(By by) {
        return getElement(by).isSelected();
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
