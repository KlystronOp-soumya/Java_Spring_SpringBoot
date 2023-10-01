package com.demo.cartapi.service.util

import org.apache.logging.log4j.LogManager

class LoggerUtil {
	
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("CONSOLE")
	
	static void info(def message)
	{
		LOGGER.info(message);
	}
}
