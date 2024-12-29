package com.csc.project.batch.jpa.spec;

import org.springframework.data.jpa.domain.Specification;

import com.csc.project.batch.dto.BatchFilter;
import com.csc.project.batch.entity.Batch;

public class BatchSpecification {

	public static Specification<Batch> buildSpecification(BatchFilter batchFilter) {
		Specification<Batch> specification = Specification.where(null);

		if (batchFilter.getSearchInput() != null && !batchFilter.getSearchInput().isBlank()) {
			String searchInput = batchFilter.getSearchInput().trim();
			specification = specification.and(searchAcrossFields(searchInput));
		}
		
        if (batchFilter.getCategory() != null) {
            specification = specification.and(filterByCategory(batchFilter.getCategory()));
        }

        if (batchFilter.getCourseId() != null) {
            specification = specification.and(filterByCourseId(batchFilter.getCourseId()));
        }

		return specification;
	}

	private static Specification<Batch> filterByCourseId(Long courseId) {
	        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("course").get("id"), courseId);
	    }

	private static Specification<Batch> searchAcrossFields(String searchInput) {
		return (root, query, criteriaBuilder) -> {
			String likePattern = "%" + searchInput.toLowerCase() + "%";
			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("classLink")), likePattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("language")), likePattern));
		};
	}
	
    private static Specification<Batch> filterByCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.join("course").get("category")),
                "%" + category.toLowerCase() + "%");
    }


}
