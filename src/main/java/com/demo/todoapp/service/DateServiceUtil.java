package com.demo.todoapp.service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateServiceUtil {
	
	static ZonedDateTime zonedDateTime = ZonedDateTime.now();

	static LocalDateTime dateTime = LocalDateTime.now(zonedDateTime.getZone());

	public static String getDate() {
		DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("YYYY-M-d", Locale.US);	
		return dateTime.format(formatterD) ;
	}
	
	public static String getTime() {
		DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("H:m:ss", Locale.US);
		return dateTime.format(formatterT) ;
	}

}
