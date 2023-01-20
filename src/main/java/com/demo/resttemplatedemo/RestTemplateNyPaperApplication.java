package com.demo.resttemplatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestTemplateNyPaperApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RestTemplateNyPaperApplication.class, args);
		TopStoriesService topStoriesService = context.getBean("topStoriesService", TopStoriesService.class);
		topStoriesService.getTopStories();
		context.close();
	}

}
