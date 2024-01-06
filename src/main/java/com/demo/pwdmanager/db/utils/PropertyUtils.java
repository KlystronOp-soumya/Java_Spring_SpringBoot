package com.demo.pwdmanager.db.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/*
 * @author KlystronOp-Soumya
 * 
 * This class loads the different properties file which 
 * 	is used in this project
 * 
 * */
public class PropertyUtils {

	public static Properties loadDatabaseProps()
	{
		Properties dbProperties = null ;

		try(
				BufferedInputStream bufferedInputStream = new BufferedInputStream(PropertyUtils.class.getClassLoader().getResourceAsStream("db-config.properties"))
				
				) {
			dbProperties = new Properties() ;
			dbProperties.load(bufferedInputStream);
			if(dbProperties.isEmpty() || null == dbProperties)
			{
				dbProperties.clear();
				throw new FileNotFoundException("Database Config file not found") ;
			}

		} catch (Exception e) {
			System.err.println(e.getCause().toString());
		}
		return dbProperties;
		
	}
	
	public Properties loadHibernateProperties()
	{
		return null;
		
	}
}
