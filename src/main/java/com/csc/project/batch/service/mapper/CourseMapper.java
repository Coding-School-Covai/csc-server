package com.csc.project.batch.service.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.entity.Course;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    public static Course courseDtoToCourse(CourseDTO courseDto) {
        if (courseDto == null) {
            return null;
        }

        Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setActive(courseDto.isActive());
        return course;
    }

    public CourseDTO courseToCourseDTO(Course course) {
        if (course == null) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setDuration(course.getDuration());
        courseDTO.setActive(course.isActive());
        return courseDTO;
    }

    public List<CourseDTO> coursesToCourseDTOs(List<Course> courses) {
        return courses.stream().map(this::courseToCourseDTO).collect(Collectors.toList());
    }

    public void updateCourseFromDto(@Valid CourseDTO courseDto, Course existingCourse) {
        existingCourse.setName(courseDto.getName());
        existingCourse.setDescription(courseDto.getDescription());
        existingCourse.setDuration(courseDto.getDuration());
        existingCourse.setActive(courseDto.isActive());
    }
}
