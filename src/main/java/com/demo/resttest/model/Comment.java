package com.demo.resttest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "COMMENTS")
@Data
public class Comment {
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
	@JsonBackReference
	private Post post;
	
	
	@Id
	@Column(name = "id")
	private long commentId;
	
	private String name;
	
	private String email;
	
	private String body;
}
