package com.demo.quarkus.jpa.artist;

import java.time.Instant;

public class Artist {

	private long id;
	private String name;
	private String bio;
	private Instant createdAt = Instant.now();

	public Artist() {

	}

	public Artist(String name, String bio) {
		this.name = name;
		this.bio = bio;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Instant getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
