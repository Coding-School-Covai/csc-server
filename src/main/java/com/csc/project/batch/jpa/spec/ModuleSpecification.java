//package com.csc.project.batch.jpa.spec;
//
//import java.util.List;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import com.csc.project.batch.dto.ModuleFilter;
//import com.csc.project.batch.entity.Modules;
//
//import org.springframework.data.jpa.domain.Specification;
//
//import jakarta.persistence.criteria.Predicate;
//
//public class ModuleSpecification {
//
//	public static Specification<Modules> buildSpecification(ModuleFilter moduleFilter) {
//		Specification<Modules> specification = Specification.where(null);
//
//		if (moduleFilter.getSubCourseId() != null) {
//			specification = specification.and(subCourseIdEquals(moduleFilter.getSubCourseId()));
//		}
//
//		if (moduleFilter.getDuration() != null) {
//			specification = specification.and(durationEquals(moduleFilter.getDuration()));
//		}
//
//		if (moduleFilter.getSearchInput() != null && !moduleFilter.getSearchInput().isBlank()) {
//			String searchInput = moduleFilter.getSearchInput().trim();
//			specification = specification.and(searchAcrossFields(searchInput));
//		}
//
//		return specification;
//	}
//
//	private static Specification<Modules> subCourseIdEquals(Long subCourseId) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("subCourse").get("id"), subCourseId);
//	}
//
//	private static Specification<Modules> durationEquals(Integer duration) {
//		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("duration"), duration);
//	}
//
//	private static Specification<Modules> searchAcrossFields(String searchInput) {
//		return (root, query, criteriaBuilder) -> {
//			String likePattern = "%" + searchInput.toLowerCase() + "%";
//			Predicate topicPredicate = criteriaBuilder.isMember(searchInput, root.get("topics"));
//			Predicate cloudFilePathPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("cloudFilePath")),
//					likePattern);
//			return criteriaBuilder.or(topicPredicate, cloudFilePathPredicate);
//		};
//	}
//}