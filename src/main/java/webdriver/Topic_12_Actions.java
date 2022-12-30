package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_12_Actions {
    String osName = System.getProperty("os.name");
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
        System.out.println(osName);
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

    @Test
    public void TC_04_Click_And_Hold() {
        visit("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> listNumber = getElements(By.cssSelector("ol#selectable>li"));
        act.clickAndHold(listNumber.get(0))
                .moveToElement(listNumber.get(7))
                .release().perform();
    }

    @Test
    public void TC_05_Click_And_Select() {
        visit("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> listNumber = getElements(By.cssSelector("ol#selectable>li"));

        if(osName.equals("Mac")) {
            act.keyDown(Keys.COMMAND);
        }
        act.clickAndHold(listNumber.get(0))
                .moveToElement(listNumber.get(7))
                .release().perform();
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
