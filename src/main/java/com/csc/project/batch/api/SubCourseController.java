package com.csc.project.batch.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.project.batch.dto.SubCourseDTO;
import com.csc.project.batch.dto.SubCourseFilter;
import com.csc.project.batch.dto.SubCoursePageResponse;
import com.csc.project.batch.service.SubCourseService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subcourses")
@Tag(name = "SubCourses API", description = "API's for managing subcourses")
public class SubCourseController {

    private final SubCourseService subCourseService;

    private static final String SUBCOURSE_CREATED_MESSAGE = "SubCourse created successfully";
    private static final String SUBCOURSE_UPDATED_MESSAGE = "SubCourse updated successfully";

    public SubCourseController(SubCourseService subCourseService) {
        this.subCourseService = subCourseService;
    }

    @PostMapping
    @Operation(summary = "Add a new subcourse", description = "Create a new subcourse linked to a course", responses = {
            @ApiResponse(responseCode = "200", description = "SubCourse created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> addSubCourse(@Valid @RequestBody SubCourseDTO subCourseDto) {
        subCourseService.addSubCourse(subCourseDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(SUBCOURSE_CREATED_MESSAGE).build());
    }

    @GetMapping("/{subCourseId}")
    @Operation(summary = "Get a subcourse by ID", description = "Retrieves a single subcourse's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "SubCourse details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubCourseDTO.class)))
    })
    public ResponseEntity<SubCourseDTO> getSubCourseById(@PathVariable Long subCourseId) {
        SubCourseDTO subCourse = subCourseService.getSubCourseById(subCourseId);
        return ResponseEntity.ok(subCourse);
    }
    
    @GetMapping
    @Operation(summary = "Get subcourses", description = "Retrieves a paginated list of subcourses based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of subcourses", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubCoursePageResponse.class)))
    })
    public ResponseEntity<SubCoursePageResponse> getSubCourses(@ModelAttribute SubCourseFilter subCourseFilter) {
        SubCoursePageResponse subCourses = subCourseService.getSubCourses(subCourseFilter);
        return ResponseEntity.ok(subCourses);
    }


    @PutMapping("/{subCourseId}")
    @Operation(summary = "Update a subcourse", description = "Updates the details of an existing subcourse by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "SubCourse updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateSubCourse(@PathVariable Long subCourseId,
            @Valid @RequestBody SubCourseDTO subCourseDto) {
        subCourseService.updateSubCourse(subCourseId, subCourseDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(SUBCOURSE_UPDATED_MESSAGE).build());
    }
}
