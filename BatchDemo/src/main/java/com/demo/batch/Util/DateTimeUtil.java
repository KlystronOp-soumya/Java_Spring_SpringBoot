package com.demo.batch.Util;

import java.util.Calendar;

import org.springframework.batch.support.ReflectionUtils;

/*
 * This class is used to handle the 
 * DateTime formatting 
 * 
 * */
@SuppressWarnings("unused")
public class DateTimeUtil {

	static Calendar calendar ;
	private static int calDay,calMonth,calYear;
	
	static
	{
		calendar = Calendar.getInstance();
		calDay = calendar.get(Calendar.DAY_OF_MONTH) ;
		calMonth = calendar.get(Calendar.MONTH) ;
		calYear = calendar.get(Calendar.YEAR) ;
	}
	
	
	public static int getCalDay() {

	return calDay ;
	}

	public static int getCalMonth() {

		return calMonth;
	}

	public static int getCalYear() {
		return calYear ;
	}
}
