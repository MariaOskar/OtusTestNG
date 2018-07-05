package ru.otus.automationpractice.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.otus.automationpractice.components.TopMenuBlock;

import java.util.List;

public class CategoryPage {

    private WebDriver driver;

    private TopMenuBlock topMenu;

    @FindBy(css = "span.category-name")
    private WebElement categoryTitleElement;

    @FindBy(css = "ul.product_list div.product-container")
    private List<WebElement> products;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        topMenu = new TopMenuBlock(driver);
    }

    @Step("Получаем название категории")
    public String getCategoryTitle(){
        return categoryTitleElement.getText();
}

    @Step("Получаем количество продуктов на странице")
    public int getProductCount(){
        return products.size();
    }

}
