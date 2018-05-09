package ru.otus.automationpractice.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.otus.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ThirdTest extends BaseTest {
    private static final int PAUSE = 1500;

    private String categoryTitle = null;
    private WebElement firstButtonElement=null;
    private WebElement categoryLink = null;

    @Given("I browse to http://automationpractice.com")
    public void i_browse_to(){
        getDriver().get("http://automationpractice.com/index.php");
    }

    @When("I focus on \"WOMEN\" button")
    public void i_focus_on_women_button(){
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

    @Then("I see menu")
    public void i_see_menu(){
        WebElement subMenu = getDriver().findElement(By.cssSelector("ul.sf-menu li:nth-of-type(1) ul"));
        assertTrue(subMenu.isDisplayed());
    }

    @When("I click on T-Shirts link")
    public void i_click_on_t_shirts_link(){
        getCategoryLink().click();
    }

    @Then("I see category page")
    public void i_see_category_page(){
        WebElement categoryTitleElement = getDriver().findElement(By.cssSelector("span.category-name"));
        assertEquals(getCategoryTitle().trim(),categoryTitleElement.getText().trim());
    }

    @Then("I see products list")
    public void i_see_products_list(){
        List<WebElement> products = getDriver().findElements(By.cssSelector("ul.product_list div.product-container"));
        assertTrue(products.size()>0);
    }

    private WebElement getFirstButtonElement(){
        if(this.firstButtonElement==null){
            firstButtonElement = getDriver().findElement(By.cssSelector("ul.sf-menu li:nth-of-type(1) a"));
        }
        return firstButtonElement;
    }

    public WebElement getCategoryLink() {
        if(this.categoryLink==null){
            categoryLink = getFirstButtonElement().findElement(By.xpath("../ul/li[1]/ul/li[1]/a"));
            categoryTitle = categoryLink.getText();
        }
        return categoryLink;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }
}
