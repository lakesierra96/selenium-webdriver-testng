package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import support.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_05_WebBrowser_Element_P2 {
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Is_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("mail")).sendKeys("Automation");
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.xpath("//label[text()='Under 18']")).isDisplayed()) {
            driver.findElement(By.id("under_18")).click();
            System.out.println("Display");

        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.id("edu")).isDisplayed()) {
            driver.findElement(By.id("edu")).sendKeys("Automation");
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Display");
        } else {
            System.out.println("Element is not displayed");
        }
    }

    @Test
    public void TC_02_MailChimp() {
        visit("https://login.mailchimp.com/signup");
        WebElement emailTextbox = getElement(By.id("email"));
        emailTextbox.sendKeys("hieu.pkt96@gmail.com");
        sleepInSecond(1);
        click((By.name("username")));
        sleepInSecond(2);

        //Get entered text from a textbox
        String usernameText = getElement(By.name("username")).getAttribute("value");
        Assert.assertEquals(usernameText, "hieu.pkt96@gmail.com");

        WebElement passwordTextbox = getElement(By.id("new_password"));
        passwordTextbox.sendKeys("aaa");
        Assert.assertTrue(getElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='uppercase-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='number-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='special-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='8-char not-completed']")));

        passwordTextbox.clear();
        passwordTextbox.sendKeys("AAA");
        Assert.assertTrue(getElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='uppercase-char completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='number-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='special-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='8-char not-completed']")));

        passwordTextbox.clear();
        passwordTextbox.sendKeys("123");
        Assert.assertTrue(getElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='uppercase-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='number-char completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='special-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='8-char not-completed']")));
        isDisplayed((By.xpath("//li[@class='8-char not-completed']")));

        passwordTextbox.clear();
        passwordTextbox.sendKeys("!@#");
        Assert.assertTrue(getElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='uppercase-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='number-char not-completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='special-char completed']")));
        Assert.assertTrue(isDisplayed(By.xpath("//li[@class='8-char not-completed']")));

        passwordTextbox.clear();
        passwordTextbox.sendKeys("Hieu@123");

        //Hàm này check nếu ko display thì in ra console
        isElementPresent(By.xpath("//li[@class='lowercase-char not-completed']"));


        //Hàm này sẽ check xem thỏa điều kiện của password chưa, các error có còn hiện ko
        List<WebElement> errorMess = driver.findElements(By.xpath("//li[contains(@class,'not-completed')]"));
        if (errorMess.size() > 0){
            System.out.println("Element is present. ");
        }
        else {
            System.out.println("Element is not present. ");
        }
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
            driver.quit();
    }
}