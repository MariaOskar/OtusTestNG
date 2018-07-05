package ru.otus.blazedemo.pages;

import io.qameta.allure.Step;
import ru.otus.blazedemo.model.OrderParams;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ru.otus.utils.TestHelper;

import java.util.List;

public class PuchasePage {
    WebDriver driver;
    @FindBy ( xpath = "//p[contains(text(), 'Flight Number')]")
    WebElement flightNumberP;
    @FindBy (xpath = "//p[contains(text(), 'Airline')]")
    WebElement airlineP;
    @FindBy (xpath = "//p[contains(text(), 'Price')]")
    WebElement priceP;
    @FindBy (xpath = "//p[contains(text(), 'Arbitrary Fees and Taxes')]")
    WebElement taxesP;
    @FindBy (xpath = "//p[contains(text(), 'Total Cost')]")
    WebElement totalCostP;
    @FindBy(css = "select[name=cardType]")
    WebElement cardTypeElement;
    @FindBy(css = "input[name=inputName]")
    WebElement inputName;
    @FindBy(css = "input[name=address]")
    WebElement address;
    @FindBy(css = "input[name=city]")
    WebElement city;
    @FindBy(css = "input[name=state]")
    WebElement state;
    @FindBy(css = "input[name=zipCode]")
    WebElement zipCode;
    @FindBy(css = "input[name=creditCardNumber]")
    WebElement creditCardNumber;
    @FindBy(css = "input[name=creditCardMonth]")
    WebElement creditCardMonth;
    @FindBy(css = "input[name=creditCardYear]")
    WebElement creditCardYear;
    @FindBy(css = "input[name=nameOnCard]")
    WebElement nameOnCard;
    @FindBy(css = "input[type=submit]")
    WebElement submit;
    private Select cardTypeSelect;


    public PuchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        cardTypeSelect = new Select(cardTypeElement);
    }

    @Step("Получаем номер рейса")
    public String getFlightNumberText(){
        return flightNumberP.getText();
    }

    @Step("Получаем название авиакомпании")
    public String getAirlineText(){
        return airlineP.getText();
    }

    @Step("Получаем цену")
    public String getPriceText(){
        return priceP.getText();
    }

    @Step("Получаем значение налогов")
    public float getTaxes(){
        return Float.parseFloat(taxesP.getText().replace("Arbitrary Fees and Taxes: ", ""));
    }

    @Step("Получаем общую стоимость")
    public float getTotalCost() {
        return Float.parseFloat(totalCostP.getText().replace("Total Cost: ", ""));
    }

    @Step("Получаем случайное значение типа банковской карты")
    public String getCardTypeValueRandom(){
        List<WebElement> cardTypeOptions = cardTypeSelect.getOptions();
       return cardTypeOptions.get(TestHelper.random(0, cardTypeOptions.size() - 1)).getAttribute("value");
    }

    @Step("Выбираем тип банковской карты")
    public void selectCardType(String cardTypeValue){
        cardTypeSelect.selectByValue(cardTypeValue);
    }

    @Step("Заполняем форму заказа")
    public void fillForm(OrderParams orderParams){
        inputName.clear();
        inputName.sendKeys(orderParams.getName());
        address.clear();
        address.sendKeys(orderParams.getAddress());
        city.clear();
        city.sendKeys(orderParams.getCity());
        state.clear();
        state.sendKeys(orderParams.getState());
        zipCode.clear();
        zipCode.sendKeys(orderParams.getState());
        creditCardNumber.clear();
        creditCardNumber.sendKeys(orderParams.getCardNumber());
        creditCardMonth.clear();
        creditCardMonth.sendKeys(orderParams.getMonth());
        creditCardYear.clear();
        creditCardYear.sendKeys(orderParams.getYear());
        nameOnCard.clear();
        nameOnCard.sendKeys(orderParams.getNameOnCard());
    }

        @Step("Отправляем заказ")
        public void submit(){
                submit.click();
        }


}
