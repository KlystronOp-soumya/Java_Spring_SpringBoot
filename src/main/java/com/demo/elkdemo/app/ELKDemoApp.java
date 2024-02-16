package com.demo.elkdemo.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo.elkdemo")
public class ELKDemoApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ELKDemoApp.class) ;
	
	public static void main(String[] args) {
		SpringApplication.run(ELKDemoApp.class , args) ;
		LOGGER.info("Application was started");
	}

}
