package com.demo.genericapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GenericSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericSpringBootAppApplication.class, args);
	}

}
