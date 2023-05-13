package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_14_Popup_Part_2 {
    WebDriverWait wait;
    Actions act;
    String emailAddress = "test" + getRandomNumber() + "@gmail.com";

    @BeforeClass
    public void beforeClass() {
        //Disable chrome notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_In_Dom() {
        visit("https://www.javacodegeeks.com/");
        By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        sleepInSecond(30);
        //vì luôn có trong dom nên có thể dùng isDisplay() để ktra
        if(isDisplayed(lePopup)) {
            sendKey(By.cssSelector("div.lepopup-input>input"), emailAddress);
            click(By.cssSelector("a.lepopup-button"));
        }
        Assert.assertTrue(isDisplayed(lePopup));
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
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
