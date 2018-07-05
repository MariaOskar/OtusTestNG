package ru.otus.blazedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    WebDriver driver;
    @FindBy (css = "table.table tbody tr:nth-of-type(1) td:nth-of-type(2)")
    WebElement id;
    @FindBy (css = "table.table tbody tr:nth-of-type(2) td:nth-of-type(2)")
    WebElement status;
    @FindBy (css = "table.table tbody tr:nth-of-type(3) td:nth-of-type(2)")
    WebElement amount;
    @FindBy (css = "table.table tbody tr:nth-of-type(4) td:nth-of-type(2)")
    WebElement cardNumber;
    @FindBy (css = "table.table tbody tr:nth-of-type(5) td:nth-of-type(2)")
    WebElement expiration;
    @FindBy (css = "table.table tbody tr:nth-of-type(6) td:nth-of-type(2)")
    WebElement authCode;
    @FindBy (css = "table.table tbody tr:nth-of-type(7) td:nth-of-type(2)")
    WebElement orderDate;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Получение идентификатора заказа")
    public String getIdText (){
        return id.getText();
    }

    @Step("Получение статуса заказа")
    public String getStatusText(){
        return status.getText();
    }

    @Step("Получение валюты")
    public String getAmountText(){
        return amount.getText();
    }

    @Step("Получение номера карты")
    public String getCardNumberVal(){
        return cardNumber.getText();
    }

    @Step("Получение даты окончания действия карты")
    public String getExpirationText(){
        return expiration.getText();
    }

    @Step("Получение кода авторизации")
    public String getAuthCodeText(){
        return authCode.getText();
    }

    @Step("Получение даты оформления заказа")
    public String getOrderDateValue(){
        return orderDate.getText();
    }

}
