package com.demo.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

/**
 * Loads classpath properties file
 */
public class PropertiesLoader {

    private static Properties props;

    /**
     * Method to load properties from the classpath
     */
    private static void loadProperties() {

        try (InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(PropKeys.DB_CONFIG_PROP.getValue())) {
            props = new Properties() ;
            props.load(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getPropValue(final String key) {
        Optional<String> valueOpt = Optional.empty();
        if (props == null ) {
            loadProperties();
        }

        valueOpt = Optional.ofNullable(props.getProperty(key));
        return valueOpt.orElseThrow(() -> new RuntimeException("key not found"));


    }
}
