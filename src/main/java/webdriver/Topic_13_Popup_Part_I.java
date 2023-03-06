package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_13_Popup_Part_I {
    String osName = System.getProperty("os.name");
    WebDriverWait wait;
    Actions act;

    @BeforeClass
    public void beforeClass() {
        //Disable chrome notification
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 30);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_NgoaiNgu() {
        visit("https://ngoaingu24h.vn/");
        By loginPopup = By.cssSelector("div.modal.fade.in div.modal-content");
        click(By.cssSelector("button.login_"));

        sendKey(By.id("account-input"), "automationfc");
        sendKey(By.id("password-input"), "automationfc");
        click(By.cssSelector("div.modal.fade.in button.btn-login-v1"));
    }

    @Test
    public void TC_02_Fixed_Popup_Kyna() {
        visit("https://skills.kynaenglish.vn/");
        By loginPopup = By.cssSelector("div#k-popup-account-login");
        click(By.cssSelector("a.login-btn"));

        Assert.assertTrue(isDisplayed(loginPopup));

        sendKey(By.id("user-login"), "automation@gmail.com");
        sendKey(By.id("user-password"), "123456");
        sleepInSecond(2);
        click(By.id("btn-submit-login"));
        Assert.assertEquals(getText(By.id("password-form-login-message")), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Fixed_Not_In_Dom_Tiki() {
        visit("https://tiki.vn/");
        By loginPopup = By.cssSelector("div.ReactModal__Overlay"); //lý do dùng findElement là vì ko trong Dom khi close popup nên ko find dc element

        //Verify popup chưa hiển thị
        Assert.assertEquals(getElements(loginPopup).size(), 0);

        click(By.xpath("//div[@data-view-id='header_header_account_container']"));

        //Verify pop-up đã hiển thị
        Assert.assertEquals(getElements(loginPopup).size(), 1);
        Assert.assertTrue(isDisplayed(loginPopup));

        Assert.assertTrue(isDisplayed(loginPopup));
        click(By.cssSelector("p.login-with-email"));
        click(By.xpath("//button[text()='Đăng nhập']"));
        isDisplayed(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']"));
        isDisplayed(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']"));

        click(By.cssSelector("img.close-img"));
        Assert.assertEquals(getElements(loginPopup).size(), 0);
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
