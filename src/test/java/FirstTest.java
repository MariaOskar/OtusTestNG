import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FirstTest {

    WebDriver driver;

    public WebDriver createFirefoxDriver (){
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public WebDriver createChromeDriver(){
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver createOperaDriver(){
        OperaOptions oo = new OperaOptions();
        oo.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
        WebDriver driver = new OperaDriver(oo);
        return driver;
    }

    public WebDriver createEdgeDriver(){
        WebDriver driver = new EdgeDriver();
        return driver;
    }

    public WebDriver getDriver(){
        String value =  System.getProperty("webdriver");
        if (value == null) value = "chrome";
        switch (value){
            case "chrome": return createChromeDriver();
            case "firefox": return createFirefoxDriver();
            case "opera": return createOperaDriver();
            case "edge": return createEdgeDriver();
            default: return createChromeDriver();
        }
    }

    @BeforeClass
    public void prepare(){
        driver = getDriver();
    }

    @Test(dataProvider = "Ports")
    public void test(String fromPortValue, String toPortValue) {

        driver.get("http://blazedemo.com/");

        new Select(driver.findElement(By.cssSelector("select[name=fromPort]"))).selectByVisibleText(fromPortValue);
        new Select(driver.findElement(By.cssSelector("select[name=toPort]"))).selectByVisibleText(toPortValue);

       driver.findElement(By.cssSelector("input[type=submit]")).click();

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("reserve.php"));

        assertEquals("http://blazedemo.com/reserve.php", driver.getCurrentUrl());

        WebElement fromPortInput = driver.findElement(By.cssSelector("input[name=fromPort]"));
        WebElement toPortInput = driver.findElement(By.cssSelector("input[name=toPort]"));

        assertEquals(fromPortValue, fromPortInput.getAttribute("value"));
        assertEquals(toPortValue, toPortInput.getAttribute("value"));
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }

    @DataProvider(name = "Ports")
    public Object[][] portValues (){
        return new Object[][] { {"Paris", "Buenos Aires"},{"Boston", "Rome"},{"San Diego", "Dublin"} };
    }

}