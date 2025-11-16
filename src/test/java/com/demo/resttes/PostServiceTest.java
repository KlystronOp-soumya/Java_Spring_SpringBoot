package com.demo.resttes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;
import com.demo.resttest.service.PostService;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

	@Mock
	private PostRepository postRepository;
	
	@InjectMocks
	private PostService postService;
	
	@Test
	void testGetAllPosts() {
		Post post1 = new Post();
		post1.setTitle("Mocked 1");
		
		Post post2 = new Post();
		post2.setTitle("Mocked 2");
		
		doReturn(Arrays.asList(post1, post2)).when(postRepository).findAll();
		
		List<Post> posts = postService.getAllPosts();
		assertThat(posts).hasSize(2);
		verify(postRepository, times(1)).findAll() ;
		verify(postRepository, never()).findById(anyLong());
		
	}
}
