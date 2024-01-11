package com.theoahga.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
    public static void load() throws IOException {
        InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        for(Map.Entry<Object, Object> entry : properties.entrySet()){
            System.setProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }
}
