package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.SubCourseDTO;
import com.csc.project.batch.entity.SubCourse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubCourseMapper {

	public SubCourse subCourseDtoToSubCourse(SubCourseDTO subCourseDto) {
		if (subCourseDto == null) {
			return null;
		}

		SubCourse subCourse = new SubCourse();
		subCourse.setId(subCourseDto.getId());
		subCourse.setName(subCourseDto.getName());
		subCourse.setActive(subCourseDto.isActive());

		return subCourse;
	}

	public SubCourseDTO subCourseToSubCourseDTO(SubCourse subCourse) {
		if (subCourse == null) {
			return null;
		}

		SubCourseDTO subCourseDTO = new SubCourseDTO();
		subCourseDTO.setId(subCourse.getId());
		subCourseDTO.setName(subCourse.getName());
		subCourseDTO.setActive(subCourse.isActive());

		return subCourseDTO;
	}

	public List<SubCourseDTO> subCoursesToSubCourseDTOs(List<SubCourse> subCourses) {
		return subCourses.stream().map(this::subCourseToSubCourseDTO).collect(Collectors.toList());
	}

	public void updateSubCourseFromDto(SubCourseDTO subCourseDto, SubCourse existingSubCourse) {
		existingSubCourse.setId(subCourseDto.getId());
		existingSubCourse.setName(subCourseDto.getName());
		existingSubCourse.setActive(subCourseDto.isActive());
	}
}
