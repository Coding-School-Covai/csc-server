package com.csc.project.common.exception;

public class ValidationException extends RuntimeException {
    private final String fieldName;
    private final String message;

    public ValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

