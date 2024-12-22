package com.csc.project.batch.jpa.spec;

import org.springframework.data.jpa.domain.Specification;

import com.csc.project.batch.dto.SubCourseFilter;
import com.csc.project.batch.entity.SubCourse;

public class SubCourseSpecification {

	public static Specification<SubCourse> buildSpecification(SubCourseFilter subCourseFilter) {
		Specification<SubCourse> specification = Specification.where(null);

		if (subCourseFilter.getSearchInput() != null && !subCourseFilter.getSearchInput().isBlank()) {
			String searchInput = subCourseFilter.getSearchInput().trim();
			specification = specification.and(searchAcrossFields(searchInput));
		}

		return specification;
	}

	private static Specification<SubCourse> searchAcrossFields(String searchInput) {
		return (root, query, criteriaBuilder) -> {
			String likePattern = "%" + searchInput.toLowerCase() + "%";
			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern));
		};
	}

}
