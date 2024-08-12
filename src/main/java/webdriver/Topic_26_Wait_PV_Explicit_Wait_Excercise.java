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

import java.io.File;
import java.util.List;

import static support.Utils.*;

public class Topic_26_Wait_PV_Explicit_Wait_Excercise {
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    String projectPath = System.getProperty("user.dir");

    String firstImg = "FirstIMG.JPG";
    String secondImg = "SecondIMG.jpg";

    String firstImgPath = projectPath + File.separator + "uploadFiles" + File.separator + firstImg;
    String secondImgPath = projectPath + File.separator + "uploadFiles" + File.separator + secondImg;

    @BeforeClass
    public void beforeClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        openBrowser();
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void TC_01_Wait_For_Ajax_Loading() {
        visit("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        WebElement todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
        Assert.assertEquals(todayText.getText(), "No Selected Dates to display.");
        System.out.println(todayText.getText());

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='9']"))).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='9']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));

        //sau khi select date thi element todayText bi thay doi trang thai, vi vay can khai bao lai de dc update thanh moi nhat neu ko se bi loi
        todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
        System.out.println(todayText.getText());
        Assert.assertEquals(todayText.getText(), "Friday, August 9, 2024");
    }

    @Test
    public void TC_02_Wait_For_Upload_Loading() {
        visit("https://gofile.io/uploadFiles");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filesLoading div.spinner-border")));

        By uploadFile = By.xpath("//input[@type='file']");
        driver.findElement(uploadFile).sendKeys(firstImgPath + "\n" + secondImgPath);

        //Wait until loading icon disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainUpload div.spinner-border")));
        //wait until progress bar disappears
        wait.until(ExpectedConditions.invisibilityOfAllElements(getElements(By.cssSelector("div.progress-bar"))));
        //How to use contains for element
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a[@class='ajaxLink']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filesContentTableContent")));

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + firstImg + "']/parent::a/parent::div//following-sibling::div//span[text()='Download']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + firstImg + "']/parent::a/parent::div//following-sibling::div//span[text()='Play']")).isDisplayed());
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
