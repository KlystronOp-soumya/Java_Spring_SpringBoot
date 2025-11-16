package com.demo.resttest.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

	private ApiCommunicator apiCommunicator;
	private final PostRepository postRepository;
	private final RestTemplate restTemplate;
	// private static final String POSTS_API =
	// "https://jsonplaceholder.typicode.com/posts";

	public PostService(PostRepository postRepository, ApiCommunicator apiCommunicator) {
		this.postRepository = postRepository;
		this.apiCommunicator = apiCommunicator;
		this.restTemplate = new RestTemplate();
	}

	public List<Post> fetchAndSavePosts() {
		// Post[] posts = this.restTemplate.getForObject(POSTS_API, Post[].class);
		
		ResponseEntity<Post[]> postsResponseEntity = restTemplate.exchange(apiCommunicator.getPostsCommunicator(),
				Post[].class);
		
		return postRepository.saveAll(Arrays.asList(postsResponseEntity.getBody()));
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
}
