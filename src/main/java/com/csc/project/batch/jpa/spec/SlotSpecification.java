package com.csc.project.batch.jpa.spec;

import org.springframework.data.jpa.domain.Specification;

import com.csc.project.batch.dto.SlotFilter;
import com.csc.project.batch.entity.Slot;

public class SlotSpecification {

	public static Specification<Slot> buildSpecification(SlotFilter slotFilter) {
		Specification<Slot> specification = Specification.where(null);

		if (slotFilter.getSearchInput() != null && !slotFilter.getSearchInput().isBlank()) {
			String searchInput = slotFilter.getSearchInput().trim();
			specification = specification.and(searchAcrossFields(searchInput));
		}

		return specification;
	}

	private static Specification<Slot> searchAcrossFields(String searchInput) {
		return (root, query, criteriaBuilder) -> {
			String likePattern = "%" + searchInput.toLowerCase() + "%";
			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
					criteriaBuilder.like(criteriaBuilder.lower(root.get("duration")), likePattern));
		};
	}

}
