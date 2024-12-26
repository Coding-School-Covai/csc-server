package com.csc.project.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentDTO {
	private Long id;
	private Long courseId;
	private double dueAmount;

}
