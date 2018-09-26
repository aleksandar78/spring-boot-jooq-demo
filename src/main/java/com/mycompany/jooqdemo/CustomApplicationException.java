package com.mycompany.jooqdemo;

public class CustomApplicationException extends RuntimeException{
	public CustomApplicationException(String error) {
		super(error);
	}
}
