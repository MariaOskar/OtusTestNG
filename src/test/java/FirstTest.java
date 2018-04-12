import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class FirstTest extends BaseTest {

    public int validDifference = 10;

    @Test
    public void test() throws ParseException {

        driver.get("http://blazedemo.com/");

        Select selectFrom = new Select(driver.findElement(By.cssSelector("select[name=fromPort]")));
        List<WebElement> fromOptions = selectFrom.getOptions();
        String fromPortValue = fromOptions.get(TestHelper.random(0, fromOptions.size() - 1)).getAttribute("value");
        selectFrom.selectByVisibleText(fromPortValue);

        Select selectTo = new Select(driver.findElement(By.cssSelector("select[name=toPort]")));
        List<WebElement> toOptions = selectTo.getOptions();
        String toPortValue = toOptions.get(TestHelper.random(0, toOptions.size() - 1)).getAttribute("value");
        selectTo.selectByVisibleText(toPortValue);

        driver.findElement(By.cssSelector("input[type=submit]")).click();

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        wait.until(ExpectedConditions.urlContains("reserve.php"));

        assertEquals("http://blazedemo.com/reserve.php", driver.getCurrentUrl());

        WebElement fromPortInput = driver.findElement(By.cssSelector("input[name=fromPort]"));
        WebElement toPortInput = driver.findElement(By.cssSelector("input[name=toPort]"));

        assertEquals(fromPortInput.getAttribute("value"), fromPortValue);
        assertEquals(toPortInput.getAttribute("value"), toPortValue);

        List<WebElement> flights = driver.findElements(By.cssSelector("table.table tbody tr"));
        WebElement flight = flights.get(TestHelper.random(0, flights.size() - 1));
        String flightNum = flight.findElement(By.cssSelector("input[name=flight]")).getAttribute("value");
        float price = Float.parseFloat(flight.findElement(By.cssSelector("input[name=price]")).getAttribute("value"));
        String airline = flight.findElement(By.cssSelector("input[name=airline]")).getAttribute("value");
        // List<WebElement> cells = flight.findElements(By.cssSelector("td"));
        // String arrivesTime = cells.get(4).getText();
        // String departsTime = cells.get(3).getText();

        flight.findElement(By.cssSelector("td input[type=submit]")).click();
        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        wait.until(ExpectedConditions.urlContains("purchase.php"));

        assertEquals("http://blazedemo.com/purchase.php", driver.getCurrentUrl());

        WebElement flightNumberP = driver.findElement(By.xpath("//p[contains(text(), 'Flight Number')]"));
        WebElement airlineP = driver.findElement(By.xpath("//p[contains(text(), 'Airline')]"));
        WebElement priceP = driver.findElement(By.xpath("//p[contains(text(), 'Price')]"));
        WebElement taxesP = driver.findElement(By.xpath("//p[contains(text(), 'Arbitrary Fees and Taxes')]"));
        WebElement totalCostP = driver.findElement(By.xpath("//p[contains(text(), 'Total Cost')]"));

        assertEquals(flightNumberP.getText(), "Flight Number: " + flightNum);
        assertEquals(airlineP.getText(), "Airline: " + airline);
        assertEquals(priceP.getText(), "Price: " + price);

        float taxes = Float.parseFloat(taxesP.getText().replace("Arbitrary Fees and Taxes: ", ""));
        float totalCost = Float.parseFloat(totalCostP.getText().replace("Total Cost: ", ""));
        TestHelper.roundFloat(price + taxes);

        assertEquals(totalCost, TestHelper.roundFloat(price + taxes));

        OrderParams orderParams = OrderParams.generate();

        Select cardTypeSelect = new Select(driver.findElement(By.cssSelector("select[name=cardType]")));
        List<WebElement> cardTypeOptions = cardTypeSelect.getOptions();
        String cardTypeValue = cardTypeOptions.get(TestHelper.random(0, cardTypeOptions.size() - 1)).getAttribute("value");
        cardTypeSelect.selectByValue(cardTypeValue);

        driver.findElement(By.cssSelector("input[name=inputName]")).sendKeys(orderParams.getName());
        driver.findElement(By.cssSelector("input[name=address]")).sendKeys(orderParams.getAddress());
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys(orderParams.getCity());
        driver.findElement(By.cssSelector("input[name=state]")).sendKeys(orderParams.getState());
        driver.findElement(By.cssSelector("input[name=zipCode]")).sendKeys(orderParams.getState());
        driver.findElement(By.cssSelector("input[name=creditCardNumber]")).sendKeys(orderParams.getCardNumber());
        driver.findElement(By.cssSelector("input[name=creditCardMonth]")).clear();
        driver.findElement(By.cssSelector("input[name=creditCardMonth]")).sendKeys(orderParams.getMonth());
        driver.findElement(By.cssSelector("input[name=creditCardYear]")).clear();
        driver.findElement(By.cssSelector("input[name=creditCardYear]")).sendKeys(orderParams.getYear());

        driver.findElement(By.cssSelector("input[name=nameOnCard]")).sendKeys(orderParams.getNameOnCard());

        driver.findElement(By.cssSelector("input[type=submit]")).click();
        // Устанавливаем часовой пояс, соответствующий временному поясу, в котором генерируется страница,
        // для получения аналогичного времени
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // Устанавливаем локаль, соответствующую дате, на странице с бронью
        Locale.setDefault(new Locale("en", "US"));
        // Устанавливаем формат даты, для парсинга строки с датой на странице брони
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Date date = new Date();
        //String now = formatter.format(date);

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        wait.until(ExpectedConditions.urlContains("confirmation.php"));

        assertEquals("http://blazedemo.com/confirmation.php", driver.getCurrentUrl());


        String id = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(1) td:nth-of-type(2)")).getText();
        String status = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(2) td:nth-of-type(2)")).getText();
        String amount = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(3) td:nth-of-type(2)")).getText();
        String cardNumberVal = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(4) td:nth-of-type(2)")).getText();
        String expiration = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(5) td:nth-of-type(2)")).getText();
        String authCode = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(6) td:nth-of-type(2)")).getText();
        String orderDateValue = driver.findElement(By.cssSelector("table.table tbody tr:nth-of-type(7) td:nth-of-type(2)")).getText();
        // парсим дату полученную со страницы
        Date orderDate =  formatter.parse(orderDateValue);

        assertEquals("PendingCapture",status);
        assertEquals("USD",amount);
        assertEquals(orderParams.getMonth()+" /"+orderParams.getYear(),expiration);
        assertEquals("888888",authCode);
        assertTrue(id.trim().length() > 0);
        assertEquals( cardNumberVal.substring( cardNumberVal.length() - 4), orderParams.getCardNumber().substring( orderParams.getCardNumber().length() - 4 ) );

        // т.к. время генерации страницы с бронью может незначительно отличаться от времени полученного во время
        // выполнения теста, то нам приходится сравнивать разницу временных меток
        // нам нужно чтобы разница во времени составляла меньше 10 секунд
        //
        // вычитаем из времени, полученного со страницы время полученное при выполнении теста
        // т.к. время указывается в милисекундах, то делим полученную разницу на 1000
        // проверяем, чтобы разница временных меток была меньше 10 секунд
        assertTrue((Math.abs(orderDate.getTime() - date.getTime())/1000) < validDifference);

    }

}





