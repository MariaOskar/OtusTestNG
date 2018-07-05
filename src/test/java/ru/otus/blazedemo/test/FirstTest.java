package ru.otus.blazedemo.test;

import ru.otus.BaseTest;
import ru.otus.blazedemo.model.OrderParams;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.otus.blazedemo.pages.ChoicePage;
import ru.otus.blazedemo.pages.ConfirmationPage;
import ru.otus.blazedemo.pages.PuchasePage;
import ru.otus.blazedemo.pages.ReservePage;
import ru.otus.utils.TestHelper;
import ru.otus.blazedemo.util.builder.OrderParamsBuilder;
import ru.otus.blazedemo.util.builder.RandomOrderParamsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class FirstTest extends BaseTest {
    private float maxPrice = 300;
    public int validDifference = 100;

    @Test
    public void test() throws ParseException {

        getDriver().get("http://blazedemo.com/");

        ChoicePage choicePage = new ChoicePage(getDriver());

        String fromPortValue = choicePage.getRandomFromOption();
        choicePage.selectFrom(fromPortValue);

        String toPortValue = choicePage.getRandomToOption();
        choicePage.selectTo(toPortValue);

        choicePage.submit();

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        getWait().until(ExpectedConditions.urlContains("reserve.php"));

        assertEquals("http://blazedemo.com/reserve.php", getDriver().getCurrentUrl());

        ReservePage reservePage = new ReservePage(getDriver());

        assertEquals(reservePage.getFromPortValue(), fromPortValue);
        assertEquals(reservePage.getToPortValue(), toPortValue);

        //reservePage.selectRandomFlight();
        // фильтрация вылетов с помощью Java 8
        reservePage.selectFilteredFlight(maxPrice);

        String flightNum = reservePage.getFlightNum();
        float price = reservePage.getPrice();
        String airline = reservePage.getAirlineCo();

        reservePage.submitFlight();

        //данный wait предназначен только для браузера Microsoft EDGE, который не дожидается загрузки страницы
        getWait().until(ExpectedConditions.urlContains("purchase.php"));

        assertEquals("http://blazedemo.com/purchase.php", getDriver().getCurrentUrl());

        PuchasePage puchasePage = new PuchasePage(getDriver());

        assertEquals(puchasePage.getFlightNumberText(), "Flight Number: " + flightNum);
        assertEquals(puchasePage.getAirlineText(), "Airline: " + airline);
        assertEquals(puchasePage.getPriceText(), "Price: " + price);

        float taxes = puchasePage.getTaxes();
        float totalCost = puchasePage.getTotalCost();

        assertEquals(totalCost, TestHelper.roundFloat(price + taxes));

        //OrderParams orderParams = buildOrderParams(new FixedOrderParamsBuilder());
        OrderParams orderParams = buildOrderParams(new RandomOrderParamsBuilder());

        String cardTypeValue = puchasePage.getCardTypeValueRandom();
        puchasePage.selectCardType(cardTypeValue);

        puchasePage.fillForm(orderParams);
        puchasePage.submit();

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
        getWait().until(ExpectedConditions.urlContains("confirmation.php"));

        assertEquals("http://blazedemo.com/confirmation.php", getDriver().getCurrentUrl());

        ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());

        // парсим дату полученную со страницы
        Date orderDate =  formatter.parse(confirmationPage.getOrderDateValue());

        assertEquals("PendingCapture",confirmationPage.getStatusText());
        assertEquals("USD",confirmationPage.getAmountText());
        assertEquals(orderParams.getMonth()+" /"+orderParams.getYear(),confirmationPage.getExpirationText());
        assertEquals("888888",confirmationPage.getAuthCodeText());
        assertTrue(confirmationPage.getIdText().trim().length() > 0);
        assertEquals( confirmationPage.getCardNumberVal().substring( confirmationPage.getCardNumberVal().length() - 4), orderParams.getCardNumber().substring( orderParams.getCardNumber().length() - 4 ) );

        // т.к. время генерации страницы с бронью может незначительно отличаться от времени полученного во время
        // выполнения теста, то нам приходится сравнивать разницу временных меток
        // нам нужно чтобы разница во времени составляла меньше 10 секунд
        //
        // вычитаем из времени, полученного со страницы время полученное при выполнении теста
        // т.к. время указывается в милисекундах, то делим полученную разницу на 1000
        // проверяем, чтобы разница временных меток была меньше 10 секунд
        assertTrue((Math.abs(orderDate.getTime() - date.getTime())/1000) < validDifference);

    }

    private OrderParams buildOrderParams(OrderParamsBuilder builder){
        builder.createOrderParams();
        builder.build();
        return builder.getOrderParams();
    }

}





