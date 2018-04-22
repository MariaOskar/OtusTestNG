package ru.otus.utils;

public class TestHelper {

    public static int random(int min, int max){
        return min + (int)Math.round(Math.random()*(max-min));
    }

    public static float roundFloat(float n){
        float k = (float) Math.round(n*100);
        return (float)k/100;
    }

    public static String getRandomArrayItem(String[] array){
        return array[random(0,array.length-1)];
    }

}
