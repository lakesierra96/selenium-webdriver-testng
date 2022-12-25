package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_12_Actions {
    Random random;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions act;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        random = new Random();
        wait = new WebDriverWait(driver, 30);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Element() {
        visit("https://automationfc.github.io/jquery-tooltip/");
        act.moveToElement(getElement(By.id("age"))).perform();
        Assert.assertTrue(isDisplayed(By.cssSelector("div.ui-tooltip-content")));
    }

    @Test
    public void TC_02_Hover_Element() {
        visit("https://www.fahasa.com/");
        act.moveToElement(getElement(By.cssSelector("span.icon_menu"))).perform();
        act.moveToElement(getElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        click(By.linkText("Quản Trị - Lãnh Đạo"));
        Assert.assertTrue(isDisplayed(By.xpath("//strong[text()='Quản Trị - Lãnh Đạo']")));
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
