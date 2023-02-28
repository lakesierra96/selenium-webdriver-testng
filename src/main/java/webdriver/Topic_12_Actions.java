package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_12_Actions {
    String osName = System.getProperty("os.name");
    Random random;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions act;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        random = new Random();
        wait = new WebDriverWait(driver, 30);
        act = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println(osName);
    }

    @Test
    public void TC_01_Hover_Element() {
        visit("https://automationfc.github.io/jquery-tooltip/");
        act.moveToElement(getElement(By.id("age"))).perform();
        Assert.assertTrue(isDisplayed(By.cssSelector("div.ui-tooltip-content")));
    }

    @Test
    public void TC_02_Hover_Element() {
        visit("https://www.fahasa.com/");
        act.moveToElement(getElement(By.cssSelector("span.icon_menu"))).perform();
        act.moveToElement(getElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        click(By.linkText("Quản Trị - Lãnh Đạo"));
        Assert.assertTrue(isDisplayed(By.xpath("//strong[text()='Quản Trị - Lãnh Đạo']")));
    }

    @Test
    public void TC_04_Click_And_Hold() {
        visit("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> listNumber = getElements(By.cssSelector("ol#selectable>li"));
        act.clickAndHold(listNumber.get(0))
                .moveToElement(listNumber.get(7))
                .release().perform();
    }

    @Test
    public void TC_05_Click_And_Select() {
        visit("https://automationfc.github.io/jquery-selectable/");
        Keys key = null;

        if (osName.equals("Windows")) {
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        List<WebElement> listNumber = getElements(By.cssSelector("ol#selectable>li"));

        act.keyDown(key).click(listNumber.get(0))
                .click(listNumber.get(2))
                .click(listNumber.get(4))
                .perform();
        act.keyUp(key).perform();

        List<WebElement> listSelectedNumber = getElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(listSelectedNumber.size(), 3);
    }

    @Test
    public void TC_06_Drag_And_Drop() {
        visit("https://automationfc.github.io/kendo-drag-drop/");
        WebElement dragElement = getElement(By.id("draggable"));
        WebElement dropTarget = getElement(By.id("droptarget"));

        act.dragAndDrop(dragElement, dropTarget).perform();

        String targetColorRGB = dropTarget.getCssValue("background-color");
        System.out.println(targetColorRGB);

        String targetBackgroundHexColor = Color.fromString(targetColorRGB).asHex();
        System.out.println(targetBackgroundHexColor);
        Assert.assertEquals(targetBackgroundHexColor, "#03a9f4");
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
