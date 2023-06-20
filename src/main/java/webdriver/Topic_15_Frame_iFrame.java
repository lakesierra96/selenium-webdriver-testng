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
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_15_Frame_iFrame {
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
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Iframe() {
        visit("https://skills.kynaenglish.vn/");
        By kynaIframe = By.cssSelector("div.fanpage iframe");

        Assert.assertTrue(isDisplayed(kynaIframe));

        driver.switchTo().frame(getElement(kynaIframe));
        String followers = getText(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div"));
        System.out.println(followers);

        Assert.assertEquals(followers, "165K followers");

        driver.switchTo().defaultContent();

        driver.switchTo().frame(getElement(By.id("cs_chat_iframe")));
        sleepInSecond(2);

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getElement(By.cssSelector("div.border_overlay")));

        sendKey(By.cssSelector("input.input_name"), "Hieu");
        sendKey(By.cssSelector("input.input_phone"), "03254646453");

        //cách để khai báo hàm select nhanh khi mà chỉ cần dùng select 1 lần (nên khai báo biến khi xài cho nhiều lần)
        new Select(getElement(By.id("serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
        sendKey(By.name("message"), "test");

        driver.switchTo().defaultContent();

        String keyword = "Excel";
        sendKey(By.id("live-search-bar"), keyword);
        click(By.cssSelector("button.search-button"));

        List <WebElement> courseName = getElements(By.cssSelector("div.content>h4"));
        for (WebElement course : courseName) {
            System.out.println(course.getText());
            Assert.assertTrue(course.getText().contains(keyword));
        }
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
