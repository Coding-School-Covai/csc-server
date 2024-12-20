package com.csc.project.common.exception;

public class ResourceNotFoundException extends CSCException{


	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
