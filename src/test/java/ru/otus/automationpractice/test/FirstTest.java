package ru.otus.automationpractice.test;

import io.qameta.allure.Description;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import ru.otus.BaseTest;
import ru.otus.automationpractice.pages.IndexPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstTest extends BaseTest {
    String colorPattern = "rgba?\\(51, 51, 51(, 1)?\\)";

    @Test(description = "Проверка стилей меню")
    @Description("Проверка стилей пункта меню при наведении курсора")
    public void test() {
        IndexPage page = new IndexPage(getDriver());

        Dimension buttonSize = page.getFirstButtonSize();
        page.focusOnFirstButton();
        // В браузерах Edge и Firefox(иногда) драйвер возвращал цвет фона элемента в формате rgb (без альфа-канала).
        // Поэтому пришлось сделать проверку регулярным выражением.
        assertTrue(page.getFirstButtonBackgroundColor().matches(colorPattern));
        assertEquals(buttonSize.height,page.getFirstButtonSize().height);
        assertEquals(buttonSize.width,page.getFirstButtonSize().width);
    }
}
