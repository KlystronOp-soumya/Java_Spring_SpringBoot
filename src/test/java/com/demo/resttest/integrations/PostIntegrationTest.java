package com.demo.resttest.integrations;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.demo.resttest.model.Post;
import com.demo.resttest.repo.PostRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostIntegrationTest {

	@LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        postRepository.deleteAll();
    }

    @Test
    void testFetchAndSavePosts_ThenRetrieve() {
        // 1️⃣ Call /posts/fetch to import posts
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .when()
            .post("/posts/fetch")
            .then()
            .statusCode(200)
            .body("size()", Matchers.greaterThan(0));

        // 2️⃣ Verify data persisted
        List<Post> posts = postRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(posts).isNotEmpty();

        // 3️⃣ Call /posts to ensure data is retrievable
        RestAssured
            .given()
            .when()
            .get("/posts")
            .then()
            .statusCode(200)
            .body("size()", Matchers.greaterThan(0))
            .body("[0].title", Matchers.notNullValue());
    }
}

