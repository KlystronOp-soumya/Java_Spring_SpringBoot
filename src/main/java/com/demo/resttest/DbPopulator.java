package com.demo.resttest;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.resttest.model.Post;
import com.demo.resttest.service.PostService;

@Component
public class DbPopulator implements ApplicationRunner {

	private PostService postService;

	public DbPopulator(PostService postService) {
		this.postService = postService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Trying to populate the database during initialization");
		List<Post> posts = postService.fetchAndSavePosts();
		System.out.println("Total %d posts were loaded".formatted(posts.size()));
	}

}
