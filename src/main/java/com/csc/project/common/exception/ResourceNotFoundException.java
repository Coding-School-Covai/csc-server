package com.csc.project.common.exception;

import java.util.List;
import java.util.stream.Collectors;

import com.csc.project.batch.dto.InstallmentDTO;
import com.csc.project.batch.entity.Installment;

public class ResourceNotFoundException extends CSCException{


	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
