package com.csc.project.batch.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {

	private Long id;
	private String cloudFilePath;
	private int duration;
	private Long subCourseId; // The ID of the related SubCourse
	private List<String> topics; // Reference to the associated SubCourse
}