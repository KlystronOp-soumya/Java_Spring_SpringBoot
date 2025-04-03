package com.demo.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

    private static final Logger LOGGER = Logger.getAnonymousLogger() ;

    public static void info(final String message)
    {
        LOGGER.setLevel(Level.INFO);
        LOGGER.info(message);
    }



}
