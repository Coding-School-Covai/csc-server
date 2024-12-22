package com.csc.project.batch.service;



import com.csc.project.batch.dto.SubCourseDTO;
import com.csc.project.batch.dto.SubCourseFilter;
import com.csc.project.batch.dto.SubCoursePageResponse;

import jakarta.validation.Valid;

public interface SubCourseService {

    void addSubCourse(SubCourseDTO subCourseDto);
    
    SubCoursePageResponse getSubCourses(SubCourseFilter subCourseFilter);

  
    SubCourseDTO getSubCourseById(Long subCourseId);

   
    void updateSubCourse(Long subCourseId, @Valid SubCourseDTO subCourseDto);

  
    SubCoursePageResponse getSubCoursesByCourseId(Long courseId);
}
