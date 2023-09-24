package com.demo.mongodemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.demo.mongodemo" })
public class AppConfig {

	@Bean(name = "mongoClient", destroyMethod = "destroy")
	public MongoClientFactoryBean mongoClientFactoryBean() {

		// ConnectionString connectionString = new
		// ConnectionString("mongodb://localhost:27017/test");

		MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
		mongoClientFactoryBean.setHost("127.0.0.1");
		mongoClientFactoryBean.setPort(27017);
		mongoClientFactoryBean.setExceptionTranslator(new MongoExceptionTranslator());

		return mongoClientFactoryBean;

	}

	@Bean("mongoTemplate")
	public MongoOperations mongoTemplate(MongoClientFactoryBean mongoClientFactoryBean) throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClientFactoryBean.getObject(), "test");
		// TODO set write concern and implement the write concern
		return mongoTemplate;
	}
}
