package com.rest.service.restful.web.services.json.filter.data;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("studentFilter")
public class Student {
	
	private String name;
	private String credentials;
	private String college;
	private int id;
	public Student(String name, String credentials, String college, int id) {
		super();
		this.name = name;
		this.credentials = credentials;
		this.college = college;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getCredentials() {
		return credentials;
	}
	public String getCollege() {
		return college;
	}
	public int getId() {
		return id;
	}

}
