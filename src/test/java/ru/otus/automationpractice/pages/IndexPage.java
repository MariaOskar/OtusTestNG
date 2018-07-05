package ru.otus.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.otus.automationpractice.components.TopMenuBlock;

public class IndexPage  {

    private WebDriver driver;

    private TopMenuBlock topMenu;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://automationpractice.com/index.php");
        PageFactory.initElements(driver, this);
        topMenu = new TopMenuBlock(driver);
    }

    @Step("Получаем размер первого пункта меню")
    public Dimension getFirstButtonSize (){
        return topMenu.getFirstButtonSize();
    }

    @Step("Получаем цвет фона первого пункта меню")
    public String getFirstButtonBackgroundColor (){
        return topMenu.getFirstButtonBackgroundColor();
    }

    @Step("Наводим курсор на первый пункт меню")
    public void focusOnFirstButton (){
        topMenu.focusOnFirstButton();
    }

    @Step("Получаем первую ссылку категории в выпадающем меню")
    public WebElement getFirstCategoryLinkElement() { return topMenu.getFirstCategoryLinkElement();}

    @Step("Получаем название первой категории в выпадающем меню")
    public String getFirstCategoryLinkText(){ return topMenu.getFirstCategoryLinkText();}

    @Step("Кликаем по первой ссылке категории в выпадающем меню")
    public CategoryPage clickOnFirstCategoryLinkElement(){
        topMenu.clickOnFirstCategoryLinkElement();
        return new CategoryPage(driver);
    }

}

