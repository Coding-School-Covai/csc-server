package com.csc.project.common.exception;

public class CSCException extends RuntimeException{

	public CSCException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSCException(String message) {
		super(message);
	}
}
