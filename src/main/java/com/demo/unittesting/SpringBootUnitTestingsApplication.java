package com.demo.unittesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.demo.unittesting" })
public class SpringBootUnitTestingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUnitTestingsApplication.class, args);
	}

}
