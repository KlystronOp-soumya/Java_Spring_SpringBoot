package com.demo.azure.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static String getISOTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) ;
    }
    
}
