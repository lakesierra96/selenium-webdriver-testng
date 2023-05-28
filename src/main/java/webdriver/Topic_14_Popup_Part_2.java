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

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_In_Dom() {
        visit("https://www.javacodegeeks.com/");
        By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        sleepInSecond(30);
        //vì luôn có trong dom nên có thể dùng isDisplay() để ktra
        //isDisplay chỉ dùng để check element có trong dom. Ko có trong dom thì fail từ findElement
        if(isDisplayed(lePopup)) {
            sendKey(By.cssSelector("div.lepopup-input>input"), emailAddress);
            click(By.cssSelector("a.lepopup-button"));
        }
        Assert.assertTrue(isDisplayed(lePopup));
        Assert.assertEquals(getText(By.cssSelector("div.lepopup-element-html-content>h4")), "Thank you!");
        //có thể dùng contains để trích ra 1 đoạn text
        Assert.assertTrue(getText(By.cssSelector("div.lepopup-element-html-content>p")).contains("Your sign-up request was successful. We will contact you shortly"));
        sleepInSecond(10);

        String articlename = "Agile Testing Explained";
        sendKey(By.id("search-input"), articlename);
        click(By.id("search-submit"));

        Assert.assertEquals(getText(By.cssSelector("ul#posts-container>li:first-child h2")), articlename);
    }

    @Test
    public void TC_02_Random_Not_In_Dom() {
        visit("https://dehieu.vn/");
        waitForElementPresent(By.cssSelector("div.popup-content"));
        By popup = By.cssSelector("div.popup-content");

        //findElement => fail khi ko tìm thấy element => ném ra lỗi
        //findElements => ko fail khi ko thấy element => trả về 1 list rỗng
        if (getElements(popup).size() > 0 && getElements(popup).get(0).isDisplayed()) {
            sendKey(By.name("name"), "Hieu");
            click(By.id("close-popup"));
        }

        String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
        click(By.linkText("Tất cả khóa học"));
        sendKey(By.id("search-courses"), courseName);
        click(By.id("search-course-button"));

        //ktra chỉ có 1 course hiển thị mathc với input
        Assert.assertEquals(getElements(By.cssSelector("div.course")).size(), 1);
        Assert.assertEquals(getText(By.cssSelector("div.course-content>h4")), courseName);
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
