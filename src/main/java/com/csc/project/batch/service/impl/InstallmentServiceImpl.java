package com.csc.project.batch.service.impl;

import com.csc.project.batch.dto.InstallmentDTO;
import com.csc.project.batch.dto.InstallmentFilter;
import com.csc.project.batch.dto.InstallmentPageResponse;
import com.csc.project.batch.entity.Installment;
import com.csc.project.batch.jpa.spec.InstallmentSpecification;
import com.csc.project.batch.repository.CourseRepository;
import com.csc.project.batch.repository.InstallmentRepository;
import com.csc.project.batch.service.InstallmentService;
import com.csc.project.batch.service.mapper.InstallmentMapper;
import com.csc.project.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService {

	private final InstallmentRepository installmentRepository;
	private final CourseRepository courseRepository;

	@Override
	public void addInstallment(InstallmentDTO installmentDTO) {
		log.info("Adding a new installment: {}", installmentDTO);

		var course = courseRepository.findById(installmentDTO.getCourseId()).orElseThrow(
				() -> new ResourceNotFoundException("Course not found with ID: " + installmentDTO.getCourseId()));

		Installment installment = InstallmentMapper.installmentDtoToEntity(installmentDTO, course);

		installmentRepository.save(installment);
		log.info("Installment added successfully.");
	}

	@Override
	public InstallmentPageResponse getInstallments(InstallmentFilter installmentFilter) {
		log.info("Fetching installments with filter: {}", installmentFilter);

		Specification<Installment> specification = InstallmentSpecification.buildSpecification(installmentFilter);

		Page<Installment> installmentPage = installmentRepository.findAll(specification, PageRequest.of(
				installmentFilter.getOffset(), installmentFilter.getLimit(),
				Sort.by(Sort.Direction.fromString(installmentFilter.getOrder()), installmentFilter.getOrderBy())));

		List<InstallmentDTO> installmentDTOs = InstallmentMapper.installmentsToDtos(installmentPage.getContent());

		return new InstallmentPageResponse(installmentPage.getTotalElements(), installmentDTOs);
	}

	@Override
	public InstallmentDTO getInstallmentById(Long installmentId) {
		log.info("Fetching installment by ID: {}", installmentId);

		Installment installment = installmentRepository.findById(installmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Installment not found with ID: " + installmentId));

		return InstallmentMapper.installmentToDto(installment);
	}

	@Override
	public void updateInstallment(Long installmentId, InstallmentDTO installmentDTO) {
		log.info("Updating installment with ID: {}", installmentId);

		Installment existingInstallment = installmentRepository.findById(installmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Installment not found with ID: " + installmentId));

		InstallmentMapper.updateInstallmentFromDto(installmentDTO, existingInstallment, courseRepository);

		installmentRepository.save(existingInstallment);
		log.info("Installment updated successfully.");
	}

}
