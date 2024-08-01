package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static support.Utils.*;

public class Topic_25_Wait_PV_Explicit_Wait {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        openBrowser();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TC_01_Wait_For_Attribute_Contains_And_ToBe_Value() {
        visit("https://live.techpanda.org/index.php");

        //Contains = gia tri tuong doi
        wait.until(ExpectedConditions.attributeContains(By.id("search"), "placeholder", "Search entire store"));
        wait.until(ExpectedConditions.attributeContains(By.id("search"), "placeholder", "Search entire store here..."));

        //ToBe = gia tri tuyet doi
        wait.until(ExpectedConditions.attributeToBe(By.id("search"), "placeholder", "Search entire store here..."));

        Assert.assertEquals(getAttribute(By.id("search"), "placeholder"), "Search entire store here...");
    }

    @Test
    public void TC_03_Wait_For_Element_Selected() {
        visit("https://automationfc.github.io/multiple-fields/");

        sendKey(By.id("first_45"), "Test");
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        System.out.println(allCheckboxes.size());
        //scrollToViewElement(By.xpath("//input[contains(text(),'Patient Medical History')]"));
        //Click all checkboxes
        for (WebElement checkbox : allCheckboxes) {
            checkbox.click();
        }

        for (WebElement checkbox : allCheckboxes) {
            wait.until(ExpectedConditions.elementToBeSelected(checkbox));
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Test
    public void TC_05_Wait_For_Number_Of_Element_To_Be() {
        visit("https://automationfc.github.io/multiple-fields/");

        sendKey(By.id("first_45"), "Test");

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input.form-checkbox"), 29));

        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        System.out.println(allCheckboxes.size());
        //scrollToViewElement(By.xpath("//input[contains(text(),'Patient Medical History')]"));
        //Click all checkboxes
        for (WebElement checkbox : allCheckboxes) {
            checkbox.click();
        }

        for (WebElement checkbox : allCheckboxes) {
            wait.until(ExpectedConditions.elementToBeSelected(checkbox));
            Assert.assertTrue(checkbox.isSelected());
        }
    }
    @Test
    public void TC_04_Wait_For_Frame() {
        visit("http://netbanking.hdfcbank.com/netbanking/");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("login_page"));
        sendKey(By.name("fldLoginUserId"), "Automation");
        click(By.linkText("CONTINUE"));

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));
        Assert.assertTrue(isDisplayed(By.id("keyboard")));
    }

    @Test
    public void TC_06_Wait_For_GetText() {
        visit("http://live.techpanda.org/index.php");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='footer']//a[@title='My Account']")));
        click(By.xpath("//div[@class='footer']//a[@title='My Account']"));

        wait.until(ExpectedConditions.elementToBeClickable(By.name("send")));
        click(By.name("send"));

        wait.until(ExpectedConditions.textToBe(By.id("advice-required-entry-email"), "This is a required field."));

        Assert.assertEquals(getText(By.id("advice-required-entry-email")), "This is a required field.");
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
