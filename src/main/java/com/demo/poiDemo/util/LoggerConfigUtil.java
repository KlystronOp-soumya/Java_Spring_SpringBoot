package com.demo.poiDemo.util;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import com.demo.poiDemo.enums.ConfigPathEnum;

/*
 * This class initializes the Log4j2
 * */
public class LoggerConfigUtil {

	public static void init() {
		try {
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			/* File can not be used */
			URL url = LoggerConfigUtil.class.getClassLoader().getResource(ConfigPathEnum.LOGGER_CONFIG_PATH.value);
			context.setConfigLocation(url.toURI());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void info(final Logger LOGGER, final String message) {
		LOGGER.info(message);
	}
}
