package com.onlineAcademy.CodingSchool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineAcademy.CodingSchool.DTO.CourseDTO;
import com.onlineAcademy.CodingSchool.Service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseService courseService;

//	@DeleteMapping("/{courseId}")
//    public ResponseEntity<String> softDeleteCourse(@PathVariable Long courseId) {
//        courseService.softDeleteCourse(courseId);
//        return ResponseEntity.ok("Course deleted successfully (soft delete)");
//    }
//
//    @DeleteMapping("/sub-courses/{subCourseId}")
//    public ResponseEntity<String> softDeleteSubCourse(@PathVariable Long subCourseId) {
//        courseService.softDeleteSubCourse(subCourseId);
//        return ResponseEntity.ok("Sub-course deleted successfully (soft delete)");
//    }

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
		return ResponseEntity.ok(courseService.createCourse(courseDTO));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<CourseDTO> getCourseByName(@PathVariable String name) {
	    return ResponseEntity.ok(courseService.getCourseByName(name));
	}
	@GetMapping
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		return ResponseEntity.ok(courseService.getAllCourses());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
		return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
}
