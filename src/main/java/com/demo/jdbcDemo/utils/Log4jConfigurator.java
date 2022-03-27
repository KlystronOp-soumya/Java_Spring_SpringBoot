package com.demo.jdbcDemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import com.demo.jdbcDemo.JdbcEnums;

public class Log4jConfigurator {
	
	public static void initializeLogger()
	{
		Properties props = new Properties() ;
		try {
			Log4jConfigurator obj = new Log4jConfigurator();
			InputStream is = obj.getLoggerConfigAsStream() ;
			props.load(is);
			DOMConfigurator.configure(props.getProperty(JdbcEnums.LOGGER_CONFIG_PATH.value));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private InputStream getLoggerConfigAsStream()
	{
		ClassLoader classLoader =this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(JdbcEnums.GENERAL_CONFIG.value);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! ");
        } else {
            return inputStream;
        }
	}
	
	// print input stream
//    private static String convertInputStream(InputStream is) {
//    	String str="" ;
//        try (InputStreamReader streamReader =
//                    new InputStreamReader(is, StandardCharsets.UTF_8);
//             BufferedReader reader = new BufferedReader(streamReader)) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//               str+=line ;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return str ;
//    }
}
