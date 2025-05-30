package com.rest.service.restful.web.services.social.media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
	@SequenceGenerator(name = "POST_SEQ", sequenceName = "POST_SEQ", allocationSize = 1, initialValue = 5000)
	@Column(name = "postid")
	private Long id;

	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;

	protected Post() {
	}

	public Post(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
}
