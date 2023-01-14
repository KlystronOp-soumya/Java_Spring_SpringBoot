package com.demo.taskdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = SimpleTaskAutoConfiguration.class)
public class BatchTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchTaskApplication.class, args);
	}

}
