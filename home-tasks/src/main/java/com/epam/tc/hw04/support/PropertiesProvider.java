package com.epam.tc.hw04.support;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertiesProvider {
    private static final String PATH_TO_PROPERTIES = "hw4/hw4.properties";
    private static Properties properties;

    @SneakyThrows
    public PropertiesProvider() {
        try (InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Incorrect file path to properties");
        }
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }
}
