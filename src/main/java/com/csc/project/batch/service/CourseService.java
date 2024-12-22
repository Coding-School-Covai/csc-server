package com.csc.project.batch.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.dto.CoursePageResponse;

import jakarta.validation.Valid;


public interface CourseService {
	 void addCourse(CourseDTO courseDTO);

	    CourseDTO getCourseById(Long id);

//	    List<CourseDTO> getAllCourses();

	    void updateCourse(Long id, @Valid CourseDTO courseDTO);

//	    void deleteCourse(Long id);
	    CourseDTO getCourseByName(String name);
	    
	    CoursePageResponse getCourses(CourseFilter courseFilter);


}
