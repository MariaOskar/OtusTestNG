package ru.otus.automationpractice.test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.otus.BaseTest;
import ru.otus.automationpractice.pages.CategoryPage;
import ru.otus.automationpractice.pages.IndexPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SecondTest extends BaseTest {

    @Test
    public void test() {
        IndexPage page = new IndexPage(driver);

        page.focusOnFirstButton();
        wait.until(ExpectedConditions.visibilityOf(page.getFirstCategoryLinkElement()));
        String categoryTitle = page.getFirstCategoryLinkText();
        CategoryPage categoryPage = page.clickOnFirstCategoryLinkElement();
        assertEquals(categoryPage.getCategoryTitle().trim(), categoryTitle.trim());
        assertTrue(categoryPage.getProductCount()>0);


    }
}
