package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_09_Handle_Button {
    Random random;
    WebDriverWait wait;
    JavascriptExecutor js;
    Select select;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        random = new Random();
        wait = new WebDriverWait(driver, 30);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Handle_Button() {
        visit("https://www.fahasa.com/customer/account/create");

        click(By.id("moe-dontallow_button"));
        driver.switchTo().frame("moe-onsite-campaign-635b4ff08dd87b080a5f46fb");
        click(By.id("close-icon"));
        Assert.assertTrue(getElement(By.cssSelector("li.popup-login-tab-login")).isEnabled());
        Assert.assertFalse(getElement(By.cssSelector("button.fhs-btn-register")).isEnabled());
        click(By.cssSelector("li.popup-login-tab-login"));
        sendKey(By.id("login_username"), "abc@gmail.com");
        sendKey(By.id("login_password"), "123456?a");
        String cssColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
        System.out.println(cssColor);
        String hexColor = Color.fromString(cssColor).asRgb();
        System.out.println(hexColor);
        Assert.assertEquals(hexColor, "rgb(201, 33, 39)");
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    public void selectItemInCustomDropdown(String itemList, String childLocator, String expectedItem) {
        click(By.id(itemList));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allItems = getElements(By.cssSelector(childLocator));
        for (WebElement item : allItems) {
            String actualItem = item.getText();
            System.out.println(actualItem);
            if (actualItem.equals(expectedItem)) {
                item.click();
            }
        }
    }

    public void scrollToElement(By element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(element));
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
