package com.csc.project.batch.service.impl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.SubCourseDTO;
import com.csc.project.batch.dto.SubCourseFilter;
import com.csc.project.batch.dto.SubCoursePageResponse;
import com.csc.project.batch.entity.SubCourse;
import com.csc.project.batch.jpa.spec.SubCourseSpecification;
import com.csc.project.batch.repository.SubCourseRepository;
import com.csc.project.batch.service.SubCourseService;
import com.csc.project.batch.service.mapper.SubCourseMapper;
import com.csc.project.common.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class SubCourseServiceImpl implements SubCourseService {

    private final SubCourseRepository subCourseRepository;
    private final SubCourseMapper subCourseMapper;

    @Override
    public void addSubCourse(SubCourseDTO subCourseDto) {
        log.info("Add subcourse with data: {}", subCourseDto);
        SubCourse subCourse = subCourseMapper.subCourseDtoToSubCourse(subCourseDto);
        subCourseRepository.save(subCourse);
        log.info("Subcourse added successfully");
    }

    @Override
    public SubCoursePageResponse getSubCourses(SubCourseFilter subCourseFilter) {
        log.info("Get all subcourses with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
                subCourseFilter.getOffset(), subCourseFilter.getLimit(), subCourseFilter.getOrder(),
                subCourseFilter.getOrderBy(), subCourseFilter.getSearchInput());

        Specification<SubCourse> specification = SubCourseSpecification.buildSpecification(subCourseFilter);

        Page<SubCourse> subCoursePage = subCourseRepository.findAll(specification,
                PageRequest.of(subCourseFilter.getOffset(), subCourseFilter.getLimit(),
                        Sort.by(Sort.Direction.fromString(subCourseFilter.getOrder()), subCourseFilter.getOrderBy())));

        List<SubCourseDTO> subCourseDTOs = subCourseMapper.subCoursesToSubCourseDTOs(subCoursePage.getContent());
        log.info("Total subcourses found: {}", subCoursePage.getTotalElements());
        return new SubCoursePageResponse(subCoursePage.getTotalElements(), subCourseDTOs);
    }

    @Override
    public SubCourseDTO getSubCourseById(Long subCourseId) {
        log.info("Get subcourse by id: {}", subCourseId);
        SubCourse subCourse = subCourseRepository.findById(subCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCourse not found with id: " + subCourseId));
        return subCourseMapper.subCourseToSubCourseDTO(subCourse);
    }

    @Override
    public void updateSubCourse(Long subCourseId, SubCourseDTO subCourseDto) {
        log.info("Update subcourse with data: {}", subCourseDto);
        SubCourse existingSubCourse = subCourseRepository.findById(subCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCourse not found with id: " + subCourseId));

        subCourseMapper.updateSubCourseFromDto(subCourseDto, existingSubCourse);
        SubCourse updatedSubCourse = subCourseRepository.save(existingSubCourse);
        log.info("Updated subcourse with id: {}", updatedSubCourse.getId());
    }

	@Override
	public SubCoursePageResponse getSubCoursesByCourseId(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}
}
