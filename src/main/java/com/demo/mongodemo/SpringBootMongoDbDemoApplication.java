package com.demo.mongodemo;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringBootMongoDbDemoApplication implements CommandLineRunner {

	/* The Logger */
	private final static Logger LOGGER = LogManager.getLogger(SpringBootMongoDbDemoApplication.class);
	private ApplicationContextProvider applicationContextProvider;
	private MongoTemplate mongoTemplate;
	private AgentService agentService;

	public SpringBootMongoDbDemoApplication(final ApplicationContextProvider applicationContextProvider) {

		this.applicationContextProvider = applicationContextProvider;

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDbDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Loading application context");
		ApplicationContext applicationContext = this.applicationContextProvider.getApplicationContext();
		this.mongoTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);
		LOGGER.info("getting the mongo client bean");
		Set<String> collectionNamesSet = this.mongoTemplate.getCollectionNames();
		Iterator<String> collectionNameSetItr = collectionNamesSet.iterator();
		LOGGER.info("Showing collection names");
		while (collectionNameSetItr.hasNext()) {
			LOGGER.debug("Collection Name: " + collectionNameSetItr.next().toString());
		}

		LOGGER.debug("Creating collection");
		if (!this.mongoTemplate.collectionExists("SPTEST"))
			this.mongoTemplate.createCollection("SPTEST");
		this.agentService = (AgentService) applicationContext.getBean("agentService", this.mongoTemplate);
		Agent agent = new Agent();
		agent.setAgtId("123456");
		agent.setAgtNo("0000254668");
		agent.setFinOwner("0000254668");
		agent.setProducerId("0100");

		Agent.Contract contract = agent.new Contract();
		contract.setContractDate(new Date("2021/03/04"));
		contract.setSignOnDate(new Date("2021/03/04"));
		agent.setContract(contract);

		LOGGER.debug("Trying to save an agent");
		this.agentService.saveAnAgent(agent);
		LOGGER.debug("Showing results");
		this.agentService.getAllAgentInfo().stream().forEach(eagent -> LOGGER.info(eagent.getProducerId()));
		System.exit(0);
	}

}
