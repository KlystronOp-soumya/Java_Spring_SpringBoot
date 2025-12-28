package com.demo.azure.servicebus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public final class PropsUtil {

    private static volatile PropsUtil instance;
    private Properties props;

    private PropsUtil() {
        loadProperties();
    }

    public static PropsUtil getInstance() {
        if (instance == null) {
            synchronized (PropsUtil.class) {
                if (instance == null) {
                    instance = new PropsUtil();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream in = PropsUtil.class.getClassLoader().getResourceAsStream("sb-config.properties");) {
            if(in == null) {
                throw new RuntimeException("sb-config.properties resource not found");
            }
            this.props = new Properties();
            this.props.load(in);
        }catch (IOException e) {
            throw new RuntimeException("properties file not found");
        }
    }

    public String getProperty(String key) {
        return this.props.getProperty(key);
    }

    // Get list property (comma-separated values)
    public List<String> getList(String key) {
        String value = this.props.getProperty(key);

        if (value == null || value.isBlank()) {
            return Collections.emptyList();
        }

        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toUnmodifiableList());
    }

}
