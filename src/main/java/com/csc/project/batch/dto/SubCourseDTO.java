package com.csc.project.batch.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCourseDTO {
	private Long id;
	private String name;
	private boolean isActive;
}
