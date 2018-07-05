package ru.otus.blazedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.otus.utils.TestHelper;

import java.util.List;
import java.util.stream.Collectors;

public class ReservePage {
    private WebDriver driver;
    private WebElement flight;

    @FindBy(css = "input[name=fromPort]")
    private WebElement fromPortInput;

    @FindBy(css ="input[name=toPort]")
    private WebElement toPortInput;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> flights;


    public ReservePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFromPortValue(){
        return fromPortInput.getAttribute("value");
    }

    public String getToPortValue(){
        return toPortInput.getAttribute("value");
    }

    public void selectRandomFlight(){
        flight = flights.get(TestHelper.random(0, flights.size() - 1));
    }

    @Step("Фильтруем рейсы по цене и выбираем один из них")
    public void selectFilteredFlight(float maxPrice){
        List<WebElement> filteredFlights = flights
                .stream()
                .filter((webElement)->{
                    String priceToken = webElement.findElement(By.cssSelector("input[name=price]")).getAttribute("value");
                    float price = Float.parseFloat(priceToken.replace("$",""));
                    return price < maxPrice;
                }).collect(Collectors.toList());
        if(filteredFlights.size()>0){
            flight = filteredFlights.get(0);
        }
    }

    @Step("Получаем номер рейса")
    public String getFlightNum(){
       if (flight!= null) return flight.findElement(By.cssSelector("input[name=flight]")).getAttribute("value");
       return null;
    }

    @Step("Получаем цену рейса")
    public float getPrice(){
        if (flight!=null) return Float.parseFloat(flight.findElement(By.cssSelector("input[name=price]")).getAttribute("value"));
        return 0;
    }

    @Step("Получаем название авиакомпании")
    public String getAirlineCo(){
        if (flight!=null) return flight.findElement(By.cssSelector("input[name=airline]")).getAttribute("value");
        return null;
    }

    @Step("Подтверждаем выбор рейса и переходим на страницу оформления заказа")
    public void submitFlight(){
        if (flight!=null) flight.findElement(By.cssSelector("td input[type=submit]")).click();
    }

}
