package com.onlineAcademy.CodingSchool.Mapper;

import org.mapstruct.*;

import com.onlineAcademy.CodingSchool.DTO.CourseDTO;
import com.onlineAcademy.CodingSchool.Entity.Course;
@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseDTO toDTO(Course course);

	Course toEntity(CourseDTO courseDTO);

	void updateEntityFromDTO(CourseDTO courseDTO, @MappingTarget Course course);

}
