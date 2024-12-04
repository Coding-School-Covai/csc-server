package com.onlineAcademy.CodingSchool.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onlineAcademy.CodingSchool.DTO.CourseDTO;

public interface CourseService {
	 CourseDTO createCourse(CourseDTO courseDTO);

//	    CourseDTO getCourseById(Long id);

	    List<CourseDTO> getAllCourses();

	    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

	    void deleteCourse(Long id);
	    CourseDTO getCourseByName(String name);


}
