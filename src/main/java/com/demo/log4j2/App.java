package com.demo.log4j2;

import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

/**
 * If src/resourcs folder is created and accessed then the below piece of code
 * is working fine Otherwise it is throwing the FileNotFound or Null Pointer
 * exception
 */
public class App {

	public static void main(String[] args) {
		try {
			// Below Working Code

			ConfigurationFactory factory = XmlConfigurationFactory.getInstance();
			/*
			 * ConfigurationSource configurationSource = new ConfigurationSource(
			 * App.class.getClassLoader().getResourceAsStream(
			 * "src/resources/logger-config.xml"));
			 */
			StringBuffer stringBuffer1 = new StringBuffer(System.getProperty("PROJDIR"));
			stringBuffer1.append('/');
			String pathString = "resources/logger-config.xml";
			stringBuffer1.append(pathString);

			ConfigurationSource configurationSource = new ConfigurationSource(
					new FileInputStream(new File(stringBuffer1.toString())));
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			Configuration configuration = factory.getConfiguration(context, configurationSource);
			context.start(configuration);
			Logger logger = LogManager.getLogger(App.class);
			logger.info("Hello World From Logger");
			/* below is a working piece of Code */
			/*
			 * LoggerContext context = (LoggerContext) LogManager.getContext(false);
			 * 
			 * URL url =
			 * App.class.getClassLoader().getResource("src/resources/logger-config.xml");
			 * context.setConfigLocation(url.toURI());
			 * LOGGER.info("This is Logged Hello World");
			 */
			// File file = new File("resources/logger-config.xml");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
