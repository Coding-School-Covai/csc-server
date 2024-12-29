//package com.csc.project.batch.jpa.spec;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import com.csc.project.batch.dto.InstallmentFilter;
//import com.csc.project.batch.entity.Installment;
//
//public class InstallmentSpecification {
//
//	public static Specification<Installment> buildSpecification(InstallmentFilter installmentFilter) {
//		Specification<Installment> specification = Specification.where(null);
//
//		if (installmentFilter.getCourseId() != null) {
//			specification = specification.and(hasCourseId(installmentFilter.getCourseId()));
//		}
//
//		if (installmentFilter.getMinAmount() != null) {
//			specification = specification.and(hasMinAmount(installmentFilter.getMinAmount()));
//		}
//
//		if (installmentFilter.getMaxAmount() != null) {
//			specification = specification.and(hasMaxAmount(installmentFilter.getMaxAmount()));
//		}
//
//		if (installmentFilter.getStatus() != null && !installmentFilter.getStatus().isBlank()) {
//			specification = specification.and(hasStatus(installmentFilter.getStatus()));
//		}
//
//		return specification;
//	}
//
//	private static Specification<Installment> hasCourseId(Long courseId) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("course").get("id"), courseId);
//	}
//
//	private static Specification<Installment> hasMinAmount(Double minAmount) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dueAmount"), minAmount);
//	}
//
//	private static Specification<Installment> hasMaxAmount(Double maxAmount) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dueAmount"), maxAmount);
//	}
//
//	private static Specification<Installment> hasStatus(String status) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
//	}
//}
