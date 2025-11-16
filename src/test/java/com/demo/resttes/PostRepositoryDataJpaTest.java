package com.demo.resttes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;

@DataJpaTest
class PostRepositoryDataJpaTest {

	private final PostRepository postRepository;

	public PostRepositoryDataJpaTest(PostRepository postRepository) {
		this.postRepository=postRepository;
	}
	
	@Test
	void testSavePost() {
		Post post = new Post();
		post.setId(1L);
		post.setTitle("Hello");
		post.setBody("World");
		post.setUserId(10L);
		
		postRepository.save(post);
		
		assertThat(postRepository.findById(1L)).isPresent();
	}
}
