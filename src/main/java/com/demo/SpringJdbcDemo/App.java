package com.demo.SpringJdbcDemo;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.SpringJdbcDemo.enums.ConstEnums;
import com.demo.SpringJdbcDemo.service.AgentService;

/**
 * Main class for the Spring JDBC ultimate Demo
 *
 * @author SOUMYADEEP PAUL
 *
 * @category Demo
 *
 * @version 1.0.0
 */
public class App {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) throws FileNotFoundException {
		try {
			LOGGER.info("Inside Main Class");

			ApplicationContext contextXML = new ClassPathXmlApplicationContext(ConstEnums.SPRING_CONTEXT_PATH.value);

			((AbstractApplicationContext) contextXML).refresh();

			AgentService agentService = (AgentService) contextXML.getBean("agentServiceBean");
			agentService.doCheck();

			/*
			 * DataSource dataSource = (DataSource) contextXML.getBean("dataSource"); if
			 * (dataSource.getConnection() != null) {
			 * LOGGER.info("DB Connection was successfull"); }
			 */
			LOGGER.info("XML based configuration was loaded successfully");
			((AbstractApplicationContext) contextXML).close();

			/*
			 * ApplicationContext contextAnnot = new
			 * AnnotationConfigApplicationContext(SpringJdbcDemoConfig.class);
			 * LOGGER.info("Annotation based configuration was loaded successfully");
			 * TestService testService = contextAnnot.getBean("testServiceBean",
			 * TestService.class); testService.doGreet(); ((AbstractApplicationContext)
			 * contextAnnot).close();
			 */
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
