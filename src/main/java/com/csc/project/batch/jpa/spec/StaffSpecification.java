//package com.csc.project.batch.jpa.spec;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import com.csc.project.batch.dto.StaffFilter;
//import com.csc.project.batch.entity.Staff;
//
//public class StaffSpecification {
//
//	public static Specification<Staff> buildSpecification(StaffFilter staffFilter) {
//		Specification<Staff> specification = Specification.where(null);
//
//		if (staffFilter.getSearchInput() != null && !staffFilter.getSearchInput().isBlank()) {
//			String searchInput = staffFilter.getSearchInput().trim();
//			specification = specification.and(searchAcrossFields(searchInput));
//		}
//
//		return specification;
//	}
//
//	private static Specification<Staff> searchAcrossFields(String searchInput) {
//		return (root, query, criteriaBuilder) -> {
//			String likePattern = "%" + searchInput.toLowerCase() + "%";
//			return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), likePattern),
//					criteriaBuilder.like(criteriaBuilder.lower(root.get("mobileNo")), likePattern),
//					criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern),
//					criteriaBuilder.like(criteriaBuilder.lower(root.get("contractNumber")), likePattern));
//		};
//	}
//
//}
