package ru.otus.blazedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ru.otus.utils.TestHelper;

import java.util.List;

public class ChoicePage {

    private WebDriver driver;

    @FindBy(css = "input[type=submit]")
    private WebElement submit;

    @FindBy(css = "select[name=fromPort]")
    private WebElement fromPortElement;

    @FindBy(css = "select[name=toPort]")
    private WebElement toPortElement;

    private Select fromPortSelect;
    private Select toPortSelect;



    public ChoicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        fromPortSelect = new Select(this.fromPortElement);
        toPortSelect = new Select(this.toPortElement);
    }

    @Step("Получаем произвольное значение порта отправки")
    public String getRandomFromOption(){
        List<WebElement> fromOptions = fromPortSelect.getOptions();
        String fromPortValue = fromOptions.get(TestHelper.random(0, fromOptions.size() - 1)).getAttribute("value");
        return fromPortValue;
    }

    @Step("Получаем произвольное значение порта назначения")
    public String getRandomToOption(){
        List<WebElement> toOptions = toPortSelect.getOptions();
        String fromPortValue = toOptions.get(TestHelper.random(0, toOptions.size() - 1)).getAttribute("value");
        return fromPortValue;
    }

    @Step("Выбираем в выпадающем списке порт назначения")
    public void selectTo(String optionValue){
        toPortSelect.selectByVisibleText(optionValue);
    }

    @Step("Выбираем в выпадающем списке порт отправки")
    public void selectFrom(String optionValue){
        fromPortSelect.selectByVisibleText(optionValue);
    }

    @Step("Подтверждаем свой выбор и отправляем форму")
    public void submit(){
        submit.submit();
    }


}
