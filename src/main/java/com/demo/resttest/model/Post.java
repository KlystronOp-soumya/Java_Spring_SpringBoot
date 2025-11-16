package com.demo.resttest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {
	@Id
	private Long id;
	private Long userId;
	private String title;
	private String body;

	@OneToMany(mappedBy = "post", // refers to the 'post' field in Comment entity
			cascade = CascadeType.ALL, // persist/delete comments along with post
			orphanRemoval = true, // delete comments if removed from list
			fetch = FetchType.LAZY // load comments only when needed
	)
	@JsonManagedReference
	List<Comment> comments;

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setPost(null);
	}

	public Post id(Long id) {
		this.id = id;
		return this;
	}

	public Post userId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Post title(String title) {
		this.title = title;
		return this;
	}

	public Post body(String body) {
		this.body = body;
		return this;
	}

}
