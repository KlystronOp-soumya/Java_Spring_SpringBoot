package com.demo.resttest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.resttest.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
