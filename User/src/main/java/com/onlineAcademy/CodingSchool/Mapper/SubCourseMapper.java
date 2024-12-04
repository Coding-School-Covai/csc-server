package com.onlineAcademy.CodingSchool.Mapper;

import com.onlineAcademy.CodingSchool.DTO.SubCourseDTO;
import com.onlineAcademy.CodingSchool.Entity.SubCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubCourseMapper {

    SubCourseDTO toDTO(SubCourse subCourse);

    SubCourse toEntity(SubCourseDTO subCourseDTO);
}
