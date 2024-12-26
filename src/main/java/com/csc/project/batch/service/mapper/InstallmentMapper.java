package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.csc.project.batch.dto.InstallmentDTO;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Installment;
import com.csc.project.batch.repository.CourseRepository;
import com.csc.project.common.exception.ResourceNotFoundException;

public class InstallmentMapper {

	public static Installment installmentDtoToEntity(InstallmentDTO installmentDTO, Course course) {
		Installment installment = new Installment();
		installment.setId(installmentDTO.getId());
		installment.setCourse(course); // Set the actual Course object
		installment.setDueAmount(installmentDTO.getDueAmount());
		return installment;
	}

	public static InstallmentDTO installmentToDto(Installment installment) {
		InstallmentDTO installmentDTO = new InstallmentDTO();
		installmentDTO.setId(installment.getId());
		installmentDTO.setCourseId(installment.getCourse().getId()); // Get the courseId from the Course object
		installmentDTO.setDueAmount(installment.getDueAmount());
		return installmentDTO;
	}

	public static List<InstallmentDTO> installmentsToDtos(List<Installment> installments) {
		return installments.stream()
				.map(InstallmentMapper::installmentToDto)
				.collect(Collectors.toList());
	}

	public static void updateInstallmentFromDto(InstallmentDTO installmentDTO, Installment installment,
			CourseRepository courseRepository) {
		Course course = courseRepository.findById(installmentDTO.getCourseId()).orElseThrow(
				() -> new ResourceNotFoundException("Course not found with ID: " + installmentDTO.getCourseId()));

		installment.setCourse(course);

		installment.setDueAmount(installmentDTO.getDueAmount());
	}
}
