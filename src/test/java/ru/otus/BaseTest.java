package ru.otus;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.otus.utils.PropertyHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

abstract public class BaseTest {
    public static int implicitWaitTimeout = 10;
    public static int explicitWaitTimeout = 10;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String seleniumGridUrl = PropertyHelper.getProperty("application.properties","selenium-grid-url");

    public static WebDriver createFirefoxDriver (){
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver createRemoteChromeDriver(){
        try {
            driver = new RemoteWebDriver(new URL(seleniumGridUrl), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неправильно сформирован URL");
        }
        return driver;
    }

    public static WebDriver createRemoteFirefoxDriver(){
        try {
            driver = new RemoteWebDriver(new URL(seleniumGridUrl), DesiredCapabilities.firefox());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неправильно сформирован URL");
        }
        return driver;
    }

    public static WebDriver createChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        if(System.getProperty("webdriver.chrome.headless")!=null && System.getProperty("webdriver.chrome.headless").equals("on")){
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
        return driver;
    }

    public static WebDriver createOperaDriver(){
        OperaOptions oo = new OperaOptions();
        oo.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
        driver = new OperaDriver(oo);
        return driver;
    }

    public static WebDriver createEdgeDriver(){
        driver = new EdgeDriver();
        return driver;
    }

    public static WebDriver getDriver(){
        if(driver == null){
            String value =  System.getProperty("webdriver");
            if (value == null) value = "chrome";
            switch (value){
                case "remote-chrome":
                    createRemoteChromeDriver();
                    break;
                case "remote-firefox":
                    createRemoteFirefoxDriver();
                    break;
                case "chrome":
                    createChromeDriver();
                    break;
                case "firefox":
                    createFirefoxDriver();
                    break;
                case "opera":
                    createOperaDriver();
                    break;
                case "edge":
                    createEdgeDriver();
                    break;
                default:
                    createChromeDriver();
                    break;
            }
            wait = new WebDriverWait(driver, explicitWaitTimeout);
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.SECONDS);
        }
        return driver;
    }

    @BeforeClass
    public void prepare(){
        getDriver();
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
}
