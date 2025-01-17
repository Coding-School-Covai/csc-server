package com.csc.project.batch.service;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.dto.CoursePageResponse;
import com.csc.project.batch.entity.Course;

import jakarta.validation.Valid;

public interface CourseService {
	void addCourse(CourseDTO courseDTO);

	Course getCourseById(Long id);

	void updateCourse(Long id, @Valid CourseDTO courseDTO);

	CoursePageResponse getCourses(CourseFilter courseFilter);
}
