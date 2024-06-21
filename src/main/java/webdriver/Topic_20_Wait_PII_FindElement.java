package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static support.Utils.*;

public class Topic_20_Wait_PII_FindElement {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    @BeforeClass
    public void beforeClass() {
        openBrowser();
        wait = new WebDriverWait(driver, 30);

    }

    @Test
    public void TC_01_Visible_Display() {
        visit("https://www.facebook.com");

        //chờ email txtbox đc hiển thị trc khi sendkey
        sendKey(By.id("email"), "Test");
    }

    @Test
    public void TC_02_Invisible_Undisplay_01() {
        //Element ko co tren UI nhung van co trong HTML
        visit("https://www.facebook.com");

        click(By.xpath("//a[text()='Create new account']"));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

        sendKey(By.name("reg_email__"), "hieu@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email_confirmation__")));

    }
    @Test
    public void TC_03_Presence_01() {
        //Dieu kien 1: Element co tren UI va co trong HTML
        visit("https://www.facebook.com");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    @Test
    public void TC_03_Presence_02() {
        //Dieu kien 2: Element ko co tren UI nhung van co trong HTML
        visit("https://www.facebook.com");
        click(By.xpath("//a[text()='Create new account']"));
        sendKey(By.name("reg_email__"), "hieu@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
    }

    @Test
    public void TC_04_Staleness() {
        //Apply both trong html va sau do dieu kien 3
        visit("https://www.facebook.com");
        click(By.xpath("//a[text()='Create new account']"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

        WebElement confirmEmail = driver.findElement(By.name("reg_email_confirmation__"));

        click(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img"));

        wait.until(ExpectedConditions.stalenessOf(confirmEmail));
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
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
