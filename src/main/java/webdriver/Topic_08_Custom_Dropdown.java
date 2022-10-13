package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_08_Custom_Dropdown {
    Random random;
    WebDriverWait wait;

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
    public void TC_01_JQuery_Dropdown() {
        visit("https://jqueryui.com/resources/demos/selectmenu/default.html");

        click(By.id("number-button"));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li.ui-menu-item")));

        List<WebElement> allItems = getElements(By.cssSelector("li.ui-menu-item"));

        for (WebElement item : allItems) {
            String actualItem = item.getText();
            System.out.println(actualItem);
            if (actualItem.equals("15")) {
                click(By.id("ui-id-15"));
            }
        }

        selectItemInCustomDropdown("salutation-button", "ul#salutation-menu li", "Mrs.");
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

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
