package ru.otus.utils;

import ru.otus.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyHelper {
    private static Map<String,Properties> propertyStorage = new HashMap<String,Properties>();

    public static String getProperty(String filename,String key){
        if(propertyStorage.containsKey(filename)==false){
            Properties properties = new Properties();
            try {
                properties.load(PropertyHelper.class.getClassLoader().getResourceAsStream(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            propertyStorage.put(filename,properties);
        }
        return propertyStorage.get(filename).getProperty(key);

    }




}
