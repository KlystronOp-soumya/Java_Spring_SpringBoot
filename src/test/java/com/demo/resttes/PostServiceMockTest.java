package com.demo.resttes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;
import com.demo.resttest.service.ApiCommunicator;
import com.demo.resttest.service.PostService;

@ExtendWith(MockitoExtension.class)
class PostServiceMockTest {

	@Mock
	private PostRepository postRepository;
	
	@Mock
	private ApiCommunicator apiCommunicator;
	
	@InjectMocks
	private PostService postService;
	
	@Test
	void testGetPost() {
		PostRepository mockPostRepository = Mockito.mock(PostRepository.class);
		ApiCommunicator mockApiCommunicator = Mockito.mock(ApiCommunicator.class);
		PostService postService = new PostService(mockPostRepository, mockApiCommunicator);
		
		 Mockito.when(mockPostRepository.save(Mockito.any(Post.class)))
         .thenReturn(new Post());
		 
		// Act
	    List<Post> posts = postService.fetchAndSavePosts();

	    // Assert
	    Mockito.verify(mockApiCommunicator, times(1)).getPostsCommunicator();
	    Mockito.verify(mockPostRepository, times(1)).save(Mockito.any(Post.class));
		
	    assertTrue(posts.size() > 0);
		
		
	}
	
	
}
