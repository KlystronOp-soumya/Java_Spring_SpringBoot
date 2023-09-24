package com.demo.mongodemo;

import java.util.Iterator;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringBootMongoDbDemoApplication implements CommandLineRunner {

	private ApplicationContextProvider applicationContextProvider;
	private MongoTemplate mongoTemplate;

	public SpringBootMongoDbDemoApplication(final ApplicationContextProvider applicationContextProvider) {

		this.applicationContextProvider = applicationContextProvider;

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDbDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext applicationContext = this.applicationContextProvider.getApplicationContext();
		this.mongoTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);
		Set<String> collectionNamesSet = this.mongoTemplate.getCollectionNames();
		Iterator<String> collectionNameSetItr = collectionNamesSet.iterator();

		while (collectionNameSetItr.hasNext()) {
			System.out.println("Collection Name: " + collectionNameSetItr.next().toString());
		}
		System.exit(0);
	}

}
