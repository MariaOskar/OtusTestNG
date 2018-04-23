package ru.otus.automationpractice.pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
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

    public Dimension getFirstButtonSize (){
        return topMenu.getFirstButtonSize();
    }

    public String getFirstButtonBackgroundColor (){
        return topMenu.getFirstButtonBackgroundColor();
    }

    public void focusOnFirstButton (){
        topMenu.focusOnFirstButton();
    }

}
