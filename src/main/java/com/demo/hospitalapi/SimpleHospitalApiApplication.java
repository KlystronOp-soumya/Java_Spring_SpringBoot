package com.demo.hospitalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SimpleHospitalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleHospitalApiApplication.class, args);
	}

}
