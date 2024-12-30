package com.csc.project.batch.service;

import java.util.List;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.dto.CoursePageResponse;
import com.csc.project.batch.entity.Course;

import jakarta.validation.Valid;


public interface CourseService {
	 void addCourse(CourseDTO courseDTO);

	    Course getCourseById(Long id);

	    List<CourseDTO> getAllCourses();

	    void updateCourse(Long id, @Valid CourseDTO courseDTO);

	    void deleteCourse(Long id);
	    
	    CoursePageResponse getCourses(CourseFilter courseFilter);


}
