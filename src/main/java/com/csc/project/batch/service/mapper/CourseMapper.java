package com.csc.project.batch.service.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.SubCourse;

import jakarta.validation.Valid;
@Component
public class CourseMapper {

    public List<Course> coursesToCourseDTOs(List<Course> courses) {
        return courses.stream().map(this::courseToCourseDTO).collect(Collectors.toList());
    }

    public void updateCourseFromDto(@Valid CourseDTO courseDto, Course existingCourse, List<SubCourse> subCourses) {
        existingCourse.setName(courseDto.getName());
        existingCourse.setDescription(courseDto.getDescription());
        existingCourse.setDuration(courseDto.getDuration());
        existingCourse.setActive(courseDto.isActive());
        existingCourse.setFees(courseDto.getFees());
        existingCourse.setCategory(courseDto.getCategory());
        existingCourse.setLevel(courseDto.getLevel());
        if (!subCourses.isEmpty()) {
            existingCourse.setSubCourses(subCourses);
        }
    }
	
    public Course courseToCourseDTO(Course course) {
        List<SubCourse> subCourseDTOs = course.getSubCourses().stream()
                .map(subCourse -> new SubCourse(subCourse.getId(), subCourse.getName(), subCourse.isActive()))
                .collect(Collectors.toList());

        return new Course(
            course.getId(),
            course.getName(),
            course.getDuration(),
            course.getFees(),
            course.getCategory(),
            course.getDescription(),
            course.getLevel(),
            subCourseDTOs,
            course.isActive()
        );
    }

    public Course courseDTOToCourse(CourseDTO courseDTO, List<SubCourse> subCourses) {
        return new Course(
            courseDTO.getId(),
            courseDTO.getName(),
            courseDTO.getDuration(),
            courseDTO.getFees(),
            courseDTO.getCategory(),
            courseDTO.getDescription(),
            courseDTO.getLevel(),
            subCourses,
            courseDTO.isActive()
        );
    }

}
