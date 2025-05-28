package com.rest.service.restful.web.services.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	
	private LocalDateTime timestamp;
	
	private String details;
	private String message;
	
	public ErrorDetails(LocalDateTime timestamp, String details, String message) {
		super();
		this.timestamp = timestamp;
		this.details = details;
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
