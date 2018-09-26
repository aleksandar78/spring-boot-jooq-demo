package com.mycompany.jooqdemo;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CustomApplicationException.class)
	public ResponseEntity<Object> handlerApplicationException(CustomApplicationException ex, WebRequest webRequest) {
		return new ResponseEntity<>(createRestError(ex), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Object> handlerSpringDataAccessException(DataAccessException ex, WebRequest webRequest) {
		return new ResponseEntity<>(createRestError(ex), HttpStatus.CONFLICT);
	}
	
	private RestError createRestError(Throwable ex) {
		RestError error = new RestError();
		error.errorMessage = ex.getMessage();
		error.rootException = ex.getClass().getCanonicalName();
		return error;
	}
	
	public static class RestError {
		public String errorMessage;
		public String rootException;
	}
	
}
