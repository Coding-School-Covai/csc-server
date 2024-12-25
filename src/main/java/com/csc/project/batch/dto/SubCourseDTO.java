package com.csc.project.batch.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubCourseDTO {
	private Long id;
	private String name;
	private boolean isActive;
}
