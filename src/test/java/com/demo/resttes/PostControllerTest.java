package com.demo.resttes;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;
import com.demo.resttest.service.PostService;

import io.restassured.RestAssured;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

	@BeforeEach
	void setup() {
		PostService mockService = Mockito.mock(PostService.class);
		PostRepository mockRepository = Mockito.mock(PostRepository.class);
		Mockito.when(mockService.getAllPosts()).thenReturn(List.of(new Post()));
		Mockito.when(mockRepository.findAll()).thenReturn(List.of(new Post().id(13L).userId(1000L)));
	}

	@Test
	void testGetAllPosts() {
		RestAssured.given().when().get("/posts").then().statusCode(200).body("size()", Matchers.greaterThan(0));
	}
}
