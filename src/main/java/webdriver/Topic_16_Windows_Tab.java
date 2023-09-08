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
        String parentID = driver.getWindowHandle();
        click(By.linkText("GOOGLE"));
        switchToWindowByTitle("Google");

        System.out.println("Page Title = " + driver.getTitle());

        switchToWindowByTitle("Selenium WebDriver");
        click(By.linkText("FACEBOOK"));
        switchToWindowByTitle("Facebook - Log in or sign up");
        System.out.println("Page Title = " + driver.getTitle());
        switchToWindowByTitle("Selenium WebDriver");
        closeAllWindowsExceptParentID(parentID);
    }

    @Test
    public void TC_03_Greater_Two_Tabs() {
        visit("http://live.techpanda.org/");
        String parentID = driver.getWindowHandle();
        click(By.xpath("//a[text()='Mobile']"));
        click(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']"));
        Assert.assertTrue(isDisplayed(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")));

        click(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']"));
        Assert.assertTrue(isDisplayed(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")));

        click(By.xpath("//button[@title='Compare']"));
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        closeAllWindowsExceptParentID(parentID);
        driver.switchTo().window(parentID);
        click(By.linkText("Clear All"));
        driver.switchTo().alert().accept();
        Assert.assertTrue(isDisplayed(By.xpath("//span[text()='The comparison list was cleared.']")));
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

    public void closeAllWindowsExceptParentID(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            driver.switchTo().window(id);
            if (!id.equals(parentID)){
                driver.close();
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
