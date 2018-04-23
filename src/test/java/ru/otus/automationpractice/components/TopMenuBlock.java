package ru.otus.automationpractice.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.otus.IComponent;

public class TopMenuBlock implements IComponent {
    private WebDriver driver;
    private static final int PAUSE = 500;
    private static final By ROOT_ELEMENT = By.id("block_top_menu");
    private static final By FIRST_BUTTON_ELEMENT = By.cssSelector("ul.sf-menu li:nth-of-type(1) a");

    public TopMenuBlock(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRootElement (){
        return driver.findElement(ROOT_ELEMENT);
    }

    public WebElement getFirstButtonElement (){
        return driver.findElement(FIRST_BUTTON_ELEMENT);
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
                .moveToElement(getFirstButtonElement(),10,10)
                .pause(PAUSE) // Firefox иногда не успевает применить стили ( в 50% случаев)
                .build()
                .perform();
    }
}

