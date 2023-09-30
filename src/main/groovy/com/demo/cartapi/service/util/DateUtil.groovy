package com.demo.cartapi.service.util

import java.text.SimpleDateFormat

import net.bytebuddy.asm.Advice.Return

class DateUtil {
	//static Date date
	//static {
		// date = new Date()
	//}
	def static String getCurrentDate() {
		Date date = new Date()
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-d");
		return formatterDate.format(date)
		
	}
	
	def static String getCurrentTime()
	{
		Date date = new Date()
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:ss");
		return formatterTime.format(date)
	}
}
