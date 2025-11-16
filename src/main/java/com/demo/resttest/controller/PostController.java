package com.demo.resttest.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.demo.resttest.model.Post;
import com.demo.resttest.service.PostService;
import java.util.List;

@RestController
@RequestMapping("/posts/v1")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/fetch")
    public List<Post> fetchAndSavePosts() {
        return postService.fetchAndSavePosts();
    }

    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}

