package ru.otus.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.otus.automationpractice.components.TopMenuBlock;

import java.util.List;

public class CategoryPage {

    private WebDriver driver;

    private TopMenuBlock topMenu;

    @FindBy(css = "#center_column > div.content_scene_cat > div > div > span.category-name")
    private WebElement categoryTitleElement;

    @FindBy(css = "#center_column > ul.product_list > li > div.product-container")
    private List<WebElement> products;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        topMenu = new TopMenuBlock(driver);
    }

    public String getCategoryTitle(){
        return categoryTitleElement.getText();
}

    public int getProductCount(){
        return products.size();
    }

}
