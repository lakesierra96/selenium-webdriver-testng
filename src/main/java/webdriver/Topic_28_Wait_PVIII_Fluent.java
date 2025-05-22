package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.function.Function;

import static support.Utils.*;

public class Topic_28_Wait_PVIII_Fluent {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    FluentWait<WebDriver> fluentWait;
    FluentWait<WebElement> elementFluentWait;
    String projectPath = System.getProperty("user.dir");

    String firstImg = "FirstIMG.JPG";
    String secondImg = "SecondIMG.jpg";

    String firstImgPath = projectPath + File.separator + "uploadFiles" + File.separator + firstImg;
    String secondImgPath = projectPath + File.separator + "uploadFiles" + File.separator + secondImg;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        openBrowser();
        wait = new WebDriverWait(driver, 30);
    }


    @Test
    public void TC_01_GetText() {
        visit("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        fluentWait = new FluentWait<WebDriver>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))  //Tổng thgian chờ
                .pollingEvery(Duration.ofMillis(100))   //Thgian tìm lại
                .ignoring(NoSuchElementException.class);

        String helloWorldText = fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return getText(By.xpath("//h4"));
            }
        });

        Assert.assertEquals(helloWorldText, "Hello World!");
    }

    @Test
    public void TC_02_Equal() {
        visit("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        fluentWait = new FluentWait<WebDriver>(driver);

        fluentWait.withTimeout(Duration.ofSeconds(15))  //Tổng thgian chờ
                .pollingEvery(Duration.ofMillis(100))   //Thgian tìm lại
                .ignoring(NoSuchElementException.class);

        fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return getElement("//h4").getText().equals("Hello World!");
            }
        });
    }

    @Test
    public void TC_03_IsDisplayed() {
        visit("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        elementFluentWait = new FluentWait<WebElement>(driver.findElement(By.xpath("//h4")));

        elementFluentWait.withTimeout(Duration.ofSeconds(15))  //Tổng thgian chờ
                .pollingEvery(Duration.ofMillis(100))   //Thgian tìm lại
                .ignoring(NoSuchElementException.class);

        fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return isDisplayed(By.xpath("//h4"));
            }
        });
    }


    @Test
    public void TC_04_Element() {
        visit("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        elementFluentWait = new FluentWait<WebElement>(driver.findElement(By.xpath("//h4")));

        elementFluentWait.withTimeout(Duration.ofSeconds(15))  //Tổng thgian chờ
                .pollingEvery(Duration.ofMillis(100))   //Thgian tìm lại
                .ignoring(NoSuchElementException.class);

        elementFluentWait.until(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement element) {
                return element.getText();
            }
        });

    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

        public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
