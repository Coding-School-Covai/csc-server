package com.csc.project.batch.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstallmentPageResponse {
	private long totalElements; 
    private List<InstallmentDTO> installments;

}
