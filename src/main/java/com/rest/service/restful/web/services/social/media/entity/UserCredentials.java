package com.rest.service.restful.web.services.social.media.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;

@Entity(name="user_credentials")
public class UserCredentials {

	@Column(unique = true)
	@Size(min = 10, message = "username should be atleast 10 characters")
	private String username;

	@JsonIgnore
	private String password;
	
	@Column(unique=true)
	private String email;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRED_SEQ")
	@SequenceGenerator(name = "CRED_SEQ", sequenceName = "CRED_SEQ", allocationSize = 1, initialValue = 1)
	private Long credId;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;
	
	public UserCredentials(String username,
			String password, String email, Long id) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.credId= id;
	}
	
	public Long getCredId() {
		return credId;
	}

	public void setCredId(Long credId) {
		this.credId = credId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
