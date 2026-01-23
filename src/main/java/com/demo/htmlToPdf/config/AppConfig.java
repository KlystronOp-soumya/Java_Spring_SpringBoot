package com.demo.htmlToPdf.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {

    private final Properties props;

    private AppConfig() {

	try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {

	    if (inputStream == null) {
		throw new FileNotFoundException("application.properties not found");
	    }

	    props = new Properties();
	    props.load(inputStream);

	} catch (Exception ex) {
	    throw new RuntimeException("Failed to load configuration", ex);
	}
    }

    private static class Holder {

	private static final AppConfig INSTANCE_APP_CONFIG = new AppConfig();
    }

    public static AppConfig getInstance() {

	return Holder.INSTANCE_APP_CONFIG;
    }

    public String get(String key) {

	return props.getProperty(key);
    }

}
