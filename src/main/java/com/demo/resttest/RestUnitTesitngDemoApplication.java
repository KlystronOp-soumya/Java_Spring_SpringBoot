package com.demo.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class RestUnitTesitngDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestUnitTesitngDemoApplication.class, args);
	}

}
