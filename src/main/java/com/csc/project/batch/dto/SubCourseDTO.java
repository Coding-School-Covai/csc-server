package com.csc.project.batch.dto;


import lombok.Data;

@Data
public class SubCourseDTO {
	private Long id;
	private String name;
	private boolean isActive;
    private Long courseId; // Reference to the parent Course

}
