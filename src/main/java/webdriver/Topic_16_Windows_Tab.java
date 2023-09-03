package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_16_Windows_Tab {
    WebDriverWait wait;
    Actions act;
    JavascriptExecutor js;
    String emailAddress = "test" + getRandomNumber() + "@gmail.com";

    @BeforeClass
    public void beforeClass() {
        //Disable chrome notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_02_Github_Greater_Two_Tabs() {
        visit("https://automationfc.github.io/basic-form/index.html");

        click(By.linkText("GOOGLE"));
        switchToWindowByTitle("Google");

        System.out.println("Page Title = " + driver.getTitle());

        switchToWindowByTitle("Selenium WebDriver");
        click(By.linkText("FACEBOOK"));
        switchToWindowByTitle("Facebook - Log in or sign up");
        System.out.println("Page Title = " + driver.getTitle());
        switchToWindowByTitle("Selenium WebDriver");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }

    public void switchToWindowByTitle(String pageTitle) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            driver.switchTo().window(id);
            String actualPageTitle = driver.getTitle();
            if (actualPageTitle.equals(pageTitle)) {
                break;
            }
        }
    }

    public boolean isElementSelected(By by) {
        return getElement(by).isSelected();
    }

    public int getRandomNumber() {
        return new Random().nextInt(99999);
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
