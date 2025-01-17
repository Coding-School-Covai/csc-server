package com.csc.project.batch.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.dto.CoursePageResponse;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.SubCourse;
import com.csc.project.batch.jpa.spec.CourseSpecification;
import com.csc.project.batch.repository.CourseRepository;
import com.csc.project.batch.repository.SubCourseRepository;
import com.csc.project.batch.service.CourseService;
import com.csc.project.batch.service.mapper.CourseMapper;
import com.csc.project.common.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;
	private final SubCourseRepository subCourseRepository;

	public void addCourse(CourseDTO courseDTO) {
		log.info("Add course with data: {}", courseDTO);
        List<SubCourse> subCourses = subCourseRepository.findAllById(
                courseDTO.getSubCourses());
        Course course = courseMapper.courseDTOToCourse(courseDTO, subCourses);
        courseRepository.save(course);
		log.info("Course added successfully");
	}

	@Override
	public CoursePageResponse getCourses(CourseFilter courseFilter) {
		log.info("Get all courses with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
				courseFilter.getOffset(), courseFilter.getLimit(), courseFilter.getOrder(), courseFilter.getOrderBy(),
				courseFilter.getSearchInput());
		Specification<Course> specification = CourseSpecification.buildSpecification(courseFilter);

		Page<Course> coursePage = courseRepository.findAll(specification,
				PageRequest.of(courseFilter.getOffset(), courseFilter.getLimit(),
						Sort.by(Sort.Direction.fromString(courseFilter.getOrder()), courseFilter.getOrderBy())));

		List<Course> courseDTOs = courseMapper.coursesToCourseDTOs(coursePage.getContent());
		log.info("Total courses found: {}", coursePage.getTotalElements());
		return new CoursePageResponse(coursePage.getTotalElements(), courseDTOs);
	}

	@Override
	public Course getCourseById(Long courseId) {
		log.info("Get course by id: {}", courseId);
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return courseMapper.courseToCourseDTO(course);
	}

	@Override
	public void updateCourse(Long courseId, @Valid CourseDTO courseDto) {
		log.info("Update course with data: {}", courseDto);
		Course existingCourse = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
	        List<SubCourse> subCourses = subCourseRepository.findAllById(courseDto.getSubCourses());
	        courseMapper.updateCourseFromDto(courseDto, existingCourse, subCourses);
	        courseRepository.save(existingCourse);
		Course updatedCourse = courseRepository.save(existingCourse);
		log.info("Updated course with id: {}", updatedCourse.getId());
	}
}
