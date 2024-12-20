package com.csc.project.common.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.csc.project.common.dto.AppResponse;
import com.csc.project.common.exception.BadRequestException;
import com.csc.project.common.exception.CSCException;
import com.csc.project.common.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred. Please try again later.";
    private static final String BAD_REQUEST = "Bad Request: ";
    private static final String RESOURCE_NOT_FOUND = "Resource Not Found: ";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppResponse<Object>> handleEntityNotFound(ResourceNotFoundException ex) {
        log.error(RESOURCE_NOT_FOUND, ex);
        return new ResponseEntity<>(AppResponse.builder().success(false).message(ex.getMessage()).build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<AppResponse<Object>> handleBadRequest(BadRequestException ex) {
        log.error(BAD_REQUEST, ex);
        return new ResponseEntity<>(AppResponse.builder().success(false).message(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CSCException.class)
    public ResponseEntity<AppResponse<Object>> handleCSCExceptions(CSCException ex) {
        log.error("Application Exception: ", ex);
        return new ResponseEntity<>(AppResponse.builder().success(false).message(ex.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppResponse<Object>> handleAllOtherExceptions(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred: ", ex);
        return new ResponseEntity<>(
                AppResponse.builder().success(false).message(UNEXPECTED_ERROR_MESSAGE)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                             .body(ex.getReason());
    }
}
