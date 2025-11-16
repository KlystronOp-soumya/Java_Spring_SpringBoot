package com.demo.resttes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;
import com.demo.resttest.service.ApiCommunicator;
import com.demo.resttest.service.PostService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostServiceSpringBootTest {

    @Mock
    private ApiCommunicator apiCommunicator;

    @Mock
    private PostRepository postRepository;

    @MockBean
    private RestTemplate restTemplate;  // Spring injects mock

    @Autowired
    private PostService postService;

    @Test
    void testFetchAndSavePosts() throws Exception {
        RequestEntity<Void> mockRequest = RequestEntity.get(new URI("https://jsonplaceholder.typicode.com/posts")).build();

        when(apiCommunicator.getPostsCommunicator()).thenReturn(mockRequest);

        Post p1 = new Post().id(1000L);
        Post p2 = new Post().id(1001L);

        ResponseEntity<Post[]> mockResponse =
                new ResponseEntity<>(new Post[] { p1, p2 }, HttpStatus.OK);

        when(restTemplate.exchange(mockRequest, Post[].class)).thenReturn(mockResponse);
        when(postRepository.saveAll(anyList())).thenReturn(List.of(p1, p2));

        List<Post> result = postService.fetchAndSavePosts();

        assertEquals(2, result.size());
    }
}
