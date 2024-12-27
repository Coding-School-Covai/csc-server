package com.csc.project.batch.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.csc.project.batch.entity.Student;
import com.csc.project.builder.StudentBuilder;
import com.csc.project.common.exception.ValidationException;
import com.csc.project.request.StudentRegisterRequest;


@Component
public class StudentMapper {

	public Student toEntity(StudentRegisterRequest registerRequest) {
		try {
			return new StudentBuilder().withFirstName(registerRequest.getFirstName())
					.withLastName(registerRequest.getLastName()).withEmail(registerRequest.getEmail())
					.withPassword(registerRequest.getPassword()).withDoj(registerRequest.getDoj())
					.withDob(registerRequest.getDob()).withMobile(registerRequest.getMobile())
					.withCurrentTopic(registerRequest.getCurrentTopic())
					.withDeviceToken(registerRequest.getDeviceToken())
					.withAadharCardNumber(registerRequest.getAadharCardNumber()).withImage(registerRequest.getImage())
					.withCollegeOrCompany(registerRequest.getCollegeOrCompany())
					.withOccupation(registerRequest.getOccupation())
					.withParentMobileNumber(registerRequest.getParentMobileNumber())
					.withRegisteredDate(registerRequest.getRegisteredDate())
					.withQualification(registerRequest.getQualification())
					.withCertificateNumber(registerRequest.getCertificateNumber())
					.withCreatedDate(registerRequest.getCreatedDate()).withUpdatedDate(registerRequest.getUpdatedDate())
					.withActive(registerRequest.isActive()).build();

		} catch (ValidationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	public StudentRegisterRequest toDto(Student student) {
		if (student == null) {
			return null;
		}

		// Map fields from the Student entity to StudentRegisterRequest
		StudentRegisterRequest dto = new StudentRegisterRequest();
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());
		dto.setEmail(student.getEmail());
		dto.setPassword(student.getPassword());
		dto.setDoj(student.getDoj());
		dto.setDob(student.getDob());
		dto.setMobile(student.getMobile());
		dto.setCurrentTopic(student.getCurrentTopic());
		dto.setDeviceToken(student.getDeviceToken());
		dto.setAadharCardNumber(student.getAadharCardNumber());
		dto.setImage(student.getImage());
		dto.setCollegeOrCompany(student.getCollegeOrCompany());
		dto.setOccupation(student.getOccupation());
		dto.setParentMobileNumber(student.getParentMobileNumber());
		dto.setRegisteredDate(student.getRegisteredDate());
		dto.setAddress(student.getAddress());
		dto.setNoOfClassesAttended(student.getNoOfClassesAttended());
		dto.setQualification(student.getQualification());
		dto.setCertificateNumber(student.getCertificateNumber());
		dto.setCreatedDate(student.getCreatedDate());
		dto.setUpdatedDate(student.getUpdatedDate());
		dto.setActive(student.isActive());

		return dto;
	}

	public List<StudentRegisterRequest> toDtoList(List<Student> students) {
		if (students == null || students.isEmpty()) {
			return List.of();
		}

		// Map each Student entity in the list to a StudentRegisterRequest DTO
		return students.stream().map(this::toDto) // Use the toDto() method for individual mapping
				.toList();
	}

}