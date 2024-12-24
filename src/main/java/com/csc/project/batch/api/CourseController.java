package com.csc.project.batch.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.dto.CoursePageResponse;
import com.csc.project.batch.service.CourseService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses API", description = "API's for managing courses")
public class CourseController {

    private final CourseService courseService;

    private static final String COURSE_CREATED_MESSAGE = "Course created successfully";
    private static final String COURSE_UPDATED_MESSAGE = "Course updated successfully";

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @Operation(summary = "Add a new course", description = "Create a new course", responses = {
            @ApiResponse(responseCode = "200", description = "Course created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> addCourse(@Valid @RequestBody CourseDTO courseDto) {
        courseService.addCourse(courseDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(COURSE_CREATED_MESSAGE).build());
    }

    @GetMapping
    @Operation(summary = "Get courses", description = "Retrieves a paginated list of courses based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of courses", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CoursePageResponse.class)))
    })
    public ResponseEntity<CoursePageResponse> getCourses(@ModelAttribute CourseFilter courseFilter) {
        CoursePageResponse courses = courseService.getCourses(courseFilter);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    @Operation(summary = "Get a course by ID", description = "Retrieves a single course's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Course details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class)))
    })
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable long courseId) {
        CourseDTO course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping("/getByName")
    @Operation(summary = "Get a course by name", description = "Retrieves a single course's details by its name.", responses = {
            @ApiResponse(responseCode = "200", description = "Course details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<CourseDTO> getCourseByName(@RequestParam String name) {
        CourseDTO course = courseService.getCourseByName(name);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{courseId}")
    @Operation(summary = "Update a course", description = "Updates the details of an existing course by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateCourse(@PathVariable long courseId,
            @Valid @RequestBody CourseDTO courseDto) {
        courseService.updateCourse(courseId, courseDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(COURSE_UPDATED_MESSAGE).build());
    }
    
    
}
