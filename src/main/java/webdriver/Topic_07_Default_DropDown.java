package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_07_Default_DropDown {
    String projectPath = System.getProperty("user.dir");
    Select select;
    Random random;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        random = new Random();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_DropDown() {
        driver.get("https://demo.nopcommerce.com/register");
        sendKey(By.cssSelector("input#FirstName"), "Hugh");
        sendKey(By.id("LastName"), "Pham");

        select = new Select(getElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText("17");

        select = new Select(getElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText("August");

        select = new Select(getElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText("1996");

        String email = "hieu.pkt" + random.nextInt(999) + "@gmail.com";
        sendKey(By.id("Email"), email);
        sendKey(By.id("Password"), "123456?a");
        sendKey(By.id("ConfirmPassword"), "123456?a");
        click(By.id("register-button"));
        Assert.assertEquals(getText(By.cssSelector("div.result")), "Your registration completed");

        click(By.linkText("My account"));

        select = new Select(getElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "17");

        select = new Select(getElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "August");

        select = new Select(getElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1996");

        Assert.assertEquals(getAttribute(By.id("Email"), "value"), email);
    }

    @Test
    public void TC_04_Default() {
        visit("https://rode.com/en/support/where-to-buy");
        select = new Select(getElement(By.id("country")));
        select.selectByVisibleText("Vietnam");
        waitForElementPresent(By.xpath("//h4[contains(text(),'Viet Thanh Company, Ltd')]"));
        List<WebElement>  dealers = driver.findElements(By.cssSelector("div#map h4"));
        for (WebElement element : dealers) {
            System.out.println(element.getText());
        }
    }

    @AfterClass
    public void afterClass() {
//            driver.quit();
    }
}