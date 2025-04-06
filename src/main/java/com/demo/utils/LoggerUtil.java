package com.demo.utils;

import org.slf4j.Logger;

import java.util.logging.Level;

public class LoggerUtil {

    public static void info(final Logger LOGGER, final String message) {
        LOGGER.info(message);
    }

    public static void debug(final Logger LOGGER, final String message) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(message);
        }
    }

    public static  void error(final Logger LOGGER , final String message){
        if(LOGGER.isErrorEnabled()){
            LOGGER.error(message);
        }
    }

}
