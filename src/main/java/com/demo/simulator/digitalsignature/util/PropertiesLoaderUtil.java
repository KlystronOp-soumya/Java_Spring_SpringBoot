package com.demo.simulator.digitalsignature.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesLoaderUtil {

	public static final PropertiesLoaderUtil INSTANCE = new PropertiesLoaderUtil();

	private final Properties properties;

	private PropertiesLoaderUtil() {

		properties = new Properties();

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {

			if (inputStream == null) {
				throw new RuntimeException("application.properties not found");
			}

			properties.load(inputStream);
		} catch (IOException ex) {
			throw new RuntimeException("Failed to load application.properties", ex);

		}
	}
}
