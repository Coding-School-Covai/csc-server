package com.csc.project.batch.jpa.spec;

import org.springframework.data.jpa.domain.Specification;

import com.csc.project.batch.dto.CourseFilter;
import com.csc.project.batch.entity.Course;

public class CourseSpecification {
	public static Specification<Course> buildSpecification(CourseFilter courseFilter) {
        Specification<Course> specification = Specification.where(null);

        if (courseFilter.getSearchInput() != null && !courseFilter.getSearchInput().isBlank()) {
            String searchInput = courseFilter.getSearchInput().trim();
            specification = specification.and(searchAcrossFields(searchInput));
        }

        return specification;
    }

    private static Specification<Course> searchAcrossFields(String searchInput) {
        return (root, query, criteriaBuilder) -> {
            String likePattern = "%" + searchInput.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("level")), likePattern)
            );
        };
    }

}
