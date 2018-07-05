package ru.otus.blazedemo.test;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SecondTest {
    @Test(description = "Проверка testng.xml")
    @Description("Заготовка теста предназначенная для проверки запуска с помощью testng.xml")
    public void test(){
        System.out.println("\nПРОВЕРКА ЗАПУСКА TEST SUITE\n");
    }
}
