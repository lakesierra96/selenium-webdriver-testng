package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static WebDriverWait wait;
    public static WebDriver driver;
    private static JavascriptExecutor js;
    static String projectPath = System.getProperty("user.dir");
    static String osName = System.getProperty("os.name");


    public static void openBrowser() {
        initBrowser("firefox");
        driver.manage().window().maximize();
    }

    public static void visit(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebElement getElement(How how, String locator) {
        return driver.findElement(how.buildBy(locator));
    }

    public static WebElement getElement(By element) {
        return driver.findElement(element);
    }

    public static List<WebElement> getElements(By element) {
        return driver.findElements(element);
    }

    public static void waitForElementPresent(How how, String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(how.buildBy(locator)));
    }

    public static void waitForElementPresent(By element) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static boolean isloadComplete() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
                || ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    public static void waitForJavascript(int maxWaitMillis, int pollDelimiter) throws InterruptedException {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = driver.getPageSource();
            Thread.sleep(pollDelimiter); // <-- would need to wrap in a try catch
            if (prevState.equals(driver.getPageSource())) {
                return;
            }
        }
    }

    public static boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Element is not present");
            return false;
        }
    }

    public static void checkPageIsReady() {

        JavascriptExecutor js = (JavascriptExecutor) driver;


        //Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            return;
        }

        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    public static void waitForPageLoaded() {
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.jsReturnsValue(
                        "return document.readyState === 'complete' ? true : false"));
    }

    public static void sendKey(By element, String withText) {
        waitForElementPresent(element);
        getElement(element).clear();
        getElement(element).sendKeys(withText);
    }

    public static void click(How how, String locator) {
        waitForElementPresent(how, locator);
        getElement(how, locator).click();
    }

    public static void click(By element) {
        waitForElementPresent(element);
        getElement(element).click();
    }

    public static void select(By selectBox, int selectIndex) {
        new Select(getElement(selectBox)).selectByIndex(selectIndex);
    }

    public static void select(By selectBox, String selectOption) {
        new Select(getElement(selectBox)).selectByVisibleText(selectOption);
    }

    public static void scrollToViewElement(By element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getElement(element));
    }

    public static void scrollToTopPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public static void scrollToBottomPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void setWait() {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public static String getText(By element) {
        waitForElementPresent(element);
        return getElement(element).getText();
    }

    public static String getAttribute(By element, String attribute) {
        waitForElementPresent(element);
        return getElement(element).getAttribute(attribute);
    }

    public static boolean isDisplayed(How how, String locator) {
        waitForElementPresent(how, locator);
        return getElement(how, locator).isDisplayed();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void focusNewTab(int tab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    public static boolean isDisplayed(By element) {
        waitForElementPresent(element);
        return getElement(element).isDisplayed();
    }

    public static boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    public static boolean isNotDisplayed(By element) {
        return getElements(element).isEmpty();
    }

    public static void initBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                if (osName.contains("Mac OS")) {
                    System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver");
                } else {
                    System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
                }
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.err.println("The browser " + browserName + "is not supported");
        }
    }

    public static void takeScreenShot(String name) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String getDateTime = dateFormat.format(cal.getTime());
        String fileWithPath = "./target/screenshots/" + name + "-" + getDateTime + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshot, DestFile);
    }

    public static void setUp() {
        openBrowser();
    }
}
