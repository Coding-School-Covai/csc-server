package com.onlineAcademy.CodingSchool.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineAcademy.CodingSchool.DTO.CourseDTO;
import com.onlineAcademy.CodingSchool.Entity.Course;
import com.onlineAcademy.CodingSchool.Mapper.CourseMapper;
import com.onlineAcademy.CodingSchool.Repo.CourseRepository;
import com.onlineAcademy.CodingSchool.Service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
    private final CourseRepository courseRepository;
	@Autowired
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    @Override
    public CourseDTO getCourseByName(String name) {
        Course course = courseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Course not found with name: " + name));
        return courseMapper.toDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        courseMapper.updateEntityFromDTO(courseDTO, existingCourse);
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }
}