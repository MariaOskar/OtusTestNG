package ru.otus;

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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import ru.otus.utils.PropertyHelper;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

abstract public class BaseTest {
    public static int implicitWaitTimeout = 10;
    public static int explicitWaitTimeout = 10;
    public static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> waitContainer = new ThreadLocal<>();
    public static String seleniumGridUrl = PropertyHelper.getProperty("application.properties","selenium-grid-url");

    public static WebDriver createFirefoxDriver (){
        driverContainer.set(new FirefoxDriver());
        return driverContainer.get();
    }

    public static WebDriver createRemoteSelenoidDriver(){
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName( PropertyHelper.getProperty("application.properties", "selenoid.browser.name") );
            capabilities.setVersion( PropertyHelper.getProperty("application.properties", "selenoid.browser.version") );
            capabilities.setCapability("enableVNC",true);
            driverContainer.set(new RemoteWebDriver(
                    URI.create(PropertyHelper.getProperty("application.properties", "selenoid.uri")).toURL(),
                    capabilities
            ));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неправильно сформирован URL");
        }
        return driverContainer.get();
    }

    public static WebDriver createRemoteChromeDriver(){
        try {
            driverContainer.set(new RemoteWebDriver(new URL(seleniumGridUrl), DesiredCapabilities.chrome()));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неправильно сформирован URL");
        }
        return driverContainer.get();
    }

    public static WebDriver createRemoteFirefoxDriver(){
        try {
            driverContainer.set(new RemoteWebDriver(new URL(seleniumGridUrl), DesiredCapabilities.firefox()));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неправильно сформирован URL");
        }
        return driverContainer.get();
    }

    public static WebDriver createChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        if(System.getProperty("webdriver.chrome.headless")!=null && System.getProperty("webdriver.chrome.headless").equals("on")){
            options.addArguments("--headless");
        }
        driverContainer.set(new ChromeDriver(options));
        return driverContainer.get();
    }

    public static WebDriver createOperaDriver(){
        OperaOptions oo = new OperaOptions();
        oo.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
        driverContainer.set(new OperaDriver(oo));
        return driverContainer.get();
    }

    public static WebDriver createEdgeDriver(){
        driverContainer.set(new EdgeDriver());
        return driverContainer.get();
    }

    public static WebDriver getDriver(){
        if(driverContainer.get() == null){
            String value =  PropertyHelper.getProperty("application.properties","webdriver");
            if (value == null) value = "chrome";
            switch (value){
                case "selenoid":
                    createRemoteSelenoidDriver();
                    break;
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
            waitContainer.set(new WebDriverWait(driverContainer.get(), explicitWaitTimeout));
            driverContainer.get().manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.SECONDS);
        }
        return driverContainer.get();
    }
    public static WebDriverWait getWait(){
        return waitContainer.get();
    }

    @BeforeClass
    public void prepare(){
        getDriver();
    }

    @AfterClass
    public void quit(){
        driverContainer.get().quit();
        driverContainer.set(null);
    }
}
