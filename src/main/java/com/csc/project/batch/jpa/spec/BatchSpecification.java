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

		return specification;
	}

	private static Specification<Batch> searchAcrossFields(String searchInput) {
		return (root, query, criteriaBuilder) -> {
			String likePattern = "%" + searchInput.toLowerCase() + "%";
			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("classLink")), likePattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("language")), likePattern));
		};
	}

}
