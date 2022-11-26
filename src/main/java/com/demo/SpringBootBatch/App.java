package com.demo.SpringBootBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.out.println("Launching the job: DB To CSV");
		SpringApplication.run(App.class, args);
	}
}
