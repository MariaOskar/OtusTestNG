package ru.otus.automationpractice.components;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import ru.otus.IComponent;

public class TopMenuBlock implements IComponent {
    private WebDriver driver;
    private static final int PAUSE = 1500;
    private static final By ROOT_ELEMENT = By.id("block_top_menu");
    private static final By FIRST_BUTTON_ELEMENT = By.cssSelector("ul.sf-menu li:nth-of-type(1) a");
    private static final By FIRST_CATEGORY_LINK = By.xpath("../ul/li[1]/ul/li[1]/a");

    public TopMenuBlock(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRootElement (){
        return driver.findElement(ROOT_ELEMENT);
    }

    public WebElement getFirstButtonElement (){
        return driver.findElement(FIRST_BUTTON_ELEMENT);
    }

    public WebElement getFirstCategoryLinkElement(){
        return getFirstButtonElement().findElement(FIRST_CATEGORY_LINK);
    }

    public Dimension getFirstButtonSize (){
        return getFirstButtonElement().getSize();
    }

    public String getFirstButtonBackgroundColor (){
        return getFirstButtonElement().getCssValue("background-color");
    }

    public void focusOnFirstButton (){
        Actions actionBuilder = new Actions(driver);
        actionBuilder
                .moveToElement(getFirstButtonElement(),10,20)
                .pause(PAUSE) // Firefox иногда не успевает применить стили ( в 50% случаев)
                .build()
                .perform();
        // MicroSoft EDGE при наведении на элемент не вызывает соответствующих событий onfocus
        if(driver.getClass().getName().equals("org.openqa.selenium.edge.EdgeDriver")){
            // вызываем данное событие используя JavascriptExecutor
            ((JavascriptExecutor)driver).executeScript("$('ul.sf-menu li:nth-of-type(1) a').focus();");
        }

    }

    public String getFirstCategoryLinkText(){
        return getFirstCategoryLinkElement().getText();
    }

    public void clickOnFirstCategoryLinkElement(){
        getFirstCategoryLinkElement().click();
    }




}

