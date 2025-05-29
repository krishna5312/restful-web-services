package com.rest.service.restful.web.services.exception;

import java.time.LocalDateTime;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), request.getDescription(false), ex.getMessage());
		return new ResponseEntity<>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUserNotException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), request.getDescription(false), ex.getMessage());
		return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), request.getDescription(false), ex.getMessage());
		return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
	}
	
	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails error = new ErrorDetails(LocalDateTime.now(),request.getDescription(false),ex.getFieldError().getDefaultMessage());

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
