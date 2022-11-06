package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static support.Utils.*;

public class Topic_10_Radio_Checkbox {
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
    public void TC_01_Default() {
        visit("https://demos.telerik.com/kendo-ui/checkbox");
        checkToCheckBoxOrRadio(getElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
        Assert.assertTrue(isElementSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
    }

    @Test
    public void TC_02_Custom() {
        visit("https://material.angular.io/components/checkbox/examples");
        /*//dùng span để click và dùng input để verify
        //disadvantage: Confuse, nhiều code để maintain
        click(By.xpath("//span[@class='mat-checkbox-inner-container']"));
        sleepInSecond(2);
        Assert.assertTrue(isSelected(getElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"))));*/
        js = (JavascriptExecutor) driver;
        WebElement checkedCheckbox = getElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input"));
        js.executeScript("arguments[0].click()", checkedCheckbox);
        Assert.assertTrue(isSelected(checkedCheckbox));
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    public void checkToCheckBoxOrRadio(By by) {
        if (!getElement(by).isSelected() && getElement(by).isEnabled()){
            click(by);
            Assert.assertTrue(isElementSelected(by));
        }
    }

    public void checkToCheckBoxOrRadio(WebElement element) {
        if (!element.isSelected() && element.isEnabled()){
            element.click();
            Assert.assertTrue(element.isSelected());
        }
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
