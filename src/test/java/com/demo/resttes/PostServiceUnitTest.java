package com.demo.resttes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;
import com.demo.resttest.service.ApiCommunicator;
import com.demo.resttest.service.PostService;

class PostServiceUnitTest {

	    private PostRepository postRepository;
	    private ApiCommunicator apiCommunicator;
	    private RestTemplate restTemplate;
	    private PostService postService;

	    @BeforeEach
	    void setup() throws Exception {
	        postRepository = mock(PostRepository.class);
	        apiCommunicator = mock(ApiCommunicator.class);
	        postService = new PostService(postRepository, apiCommunicator);

	        // Inject mock restTemplate using reflection
	        restTemplate = mock(RestTemplate.class);

	        Field restTemplateField = PostService.class.getDeclaredField("restTemplate");
	        restTemplateField.setAccessible(true);
	        restTemplateField.set(postService, restTemplate);
	    }

	    @Test
	    void testFetchAndSavePosts() throws Exception {

	        // Mock request entity from ApiCommunicator
	        RequestEntity<Void> mockRequest = RequestEntity
	                        .get(new URI("https://jsonplaceholder.typicode.com/posts"))
	                        .build();

	        when(apiCommunicator.getPostsCommunicator()).thenReturn(mockRequest);

	        // Mock API response
	        Post p1 = new Post().id(1000L);
	        Post p2 = new Post().id(1001L);
	        Post[] postsArray = { p1, p2 };

	        ResponseEntity<Post[]> mockResponse =
	                new ResponseEntity<>(postsArray, HttpStatus.OK);

	        when(restTemplate.exchange(
	                mockRequest,
	                Post[].class
	        )).thenReturn(mockResponse);

	        // Mock DB save
	        when(postRepository.saveAll(anyList())).thenReturn(Arrays.asList(p1, p2));

	        // Execute
	        List<Post> result = postService.fetchAndSavePosts();

	        // Validate
	        assertEquals(2, result.size());
	        verify(postRepository, times(1)).saveAll(anyList());
	        verify(apiCommunicator, times(1)).getPostsCommunicator();
	        verify(restTemplate, times(1)).exchange(mockRequest, Post[].class);
	    }

	    @Test
	    void testGetAllPosts() {
	        when(postRepository.findAll()).thenReturn(List.of());
	        assertTrue(postService.getAllPosts().isEmpty());
	    }

}
