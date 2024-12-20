package com.csc.project.common.exception;

public class BadRequestException extends CSCException{

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}
}
