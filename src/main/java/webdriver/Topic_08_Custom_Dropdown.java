package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    JavascriptExecutor js;
    Select select;

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

        selectItemInCustomDropdown("speed-button", "ul#speed-menu li", "Faster");
    }

    @Test
    public void TC_02_JQuery_Dropdown_02() {
        visit("https://www.honda.com.vn/o-to/du-toan-chi-phi");

        scrollToElement(By.cssSelector("div[class='carousel-item active home-page-slide-top']"));
        //Co the viet cssLocator nhu tren hoac viet bang cach 2 thi chi can lay 1 phan div.carousel-item

        sleepInSecond(3);

        selectItemInCustomDropdown("selectize-input", "button#selectize-input+div>a", "CR-V LSE (Đen Ánh)");

        new Select(getElement(By.name("province"))).selectByVisibleText("Cần Thơ");

        new Select(getElement(By.name("registration_fee"))).selectByVisibleText("Khu vực II");

    }

    @Test
    public void TC_03_JQuery_Drodown_02() {
        visit("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        sleepInSecond(3);
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

    public void scrollToElement(By element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(element));
    }

    public void sleepInSecond(long miliSecond) {
        try {
            Thread.sleep(miliSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
