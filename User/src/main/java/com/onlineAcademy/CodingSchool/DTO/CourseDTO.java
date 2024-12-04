package com.onlineAcademy.CodingSchool.DTO;

import lombok.Data;
import java.util.List;

@Data
public class CourseDTO {
	private Long id;
	private String name;
	private int duration;
	private double fees;
	private List<SubCourseDTO> subCourses;
	private boolean isActive;
}
