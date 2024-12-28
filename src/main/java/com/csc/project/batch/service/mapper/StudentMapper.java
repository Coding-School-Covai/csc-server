package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.AddressDTO;
import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Status;
import com.csc.project.batch.entity.Student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentMapper {

	public static Student studentDtoToStudent(StudentDTO studentDto) {
		if (studentDto == null) {
			return null;
		}

		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setDob(studentDto.getDob());
		student.setMobile(studentDto.getMobile());
		student.setCurrentTopic(studentDto.getCurrentTopic());
		student.setDeviceToken(studentDto.getDeviceToken());
		student.setAadharCardNumber(studentDto.getAadharCardNumber());
		student.setImage(studentDto.getImage());
		student.setCollegeOrCompany(studentDto.getCollegeOrCompany());
		student.setOccupation(studentDto.getOccupation());
		student.setParentMobileNumber(studentDto.getParentMobileNumber());
		student.setRegisteredDate(studentDto.getRegisteredDate());
		student.setNoOfClassesAttended(studentDto.getNoOfClassesAttended());
		student.setQualification(studentDto.getQualification());
		student.setCertificateNumber(studentDto.getCertificateNumber());
		student.setActive(studentDto.isActive());

		student.setStatus(student.getStatus() != null ? student.getStatus() : Status.NOT_VERIFIED);

		if (studentDto.getAddress() != null) {
			Address address = new Address();
			address.setId(studentDto.getAddress().getId());
			student.setAddress(address);
		}

		if (studentDto.getBatch() != null) {
			Batch batch = new Batch();
			batch.setId(studentDto.getBatch().getId());
			student.setBatch(batch);
		}

		if (studentDto.getCourse() != null) {
			Course course = new Course();
			course.setId(studentDto.getCourse().getId());
			student.setCourse(course);
		}

		return student;
	}

	public StudentDTO studentToStudentDTO(Student student) {
		if (student == null) {
			return null;
		}

		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName(student.getFirstName());
		studentDTO.setLastName(student.getLastName());
		studentDTO.setEmail(student.getEmail());
		studentDTO.setPassword(student.getPassword());
		studentDTO.setDob(student.getDob());
		studentDTO.setMobile(student.getMobile());
		studentDTO.setCurrentTopic(student.getCurrentTopic());
		studentDTO.setDeviceToken(student.getDeviceToken());
		studentDTO.setAadharCardNumber(student.getAadharCardNumber());
		studentDTO.setImage(student.getImage());
		studentDTO.setCollegeOrCompany(student.getCollegeOrCompany());
		studentDTO.setOccupation(student.getOccupation());
		studentDTO.setParentMobileNumber(student.getParentMobileNumber());
		studentDTO.setRegisteredDate(student.getRegisteredDate());
		studentDTO.setNoOfClassesAttended(student.getNoOfClassesAttended());
		studentDTO.setQualification(student.getQualification());
		studentDTO.setCertificateNumber(student.getCertificateNumber());
		studentDTO.setActive(student.isActive());

		studentDTO.setStatus((student.getStatus() != null) ? student.getStatus() : Status.NOT_VERIFIED);

		if (student.getAddress() != null) {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setId(student.getAddress().getId());
			addressDTO.setStreet(student.getAddress().getStreet());
			addressDTO.setCity(student.getAddress().getCity());
			addressDTO.setState(student.getAddress().getState());
			addressDTO.setCountry(student.getAddress().getCountry());
			addressDTO.setZipcode(student.getAddress().getZipcode());
			studentDTO.setAddress(addressDTO);
		}

		if (student.getBatch() != null) {
			BatchDTO batchDTO = new BatchDTO();
			batchDTO.setId(student.getBatch().getId());
			batchDTO.setStartDate(student.getBatch().getStartDate());
			batchDTO.setEndDate(student.getBatch().getEndDate());
			studentDTO.setBatch(batchDTO);
		}

		if (student.getCourse() != null) {
			CourseDTO courseDTO = new CourseDTO();
			courseDTO.setId(student.getCourse().getId());
			courseDTO.setName(student.getCourse().getName());
			courseDTO.setDuration(student.getCourse().getDuration());
			courseDTO.setFees(student.getCourse().getFees());
			studentDTO.setCourse(courseDTO);
		}

		return studentDTO;
	}

	public List<StudentDTO> studentsToStudentDTOs(List<Student> students) {
		if (students == null || students.isEmpty()) {
			return List.of();
		}

		return students.stream().map(this::studentToStudentDTO).collect(Collectors.toList());
	}

	public void updateStudentFromDto(@Valid StudentDTO studentDto, Student existingStudent) {
		if (studentDto == null || existingStudent == null) {
			return;
		}

		List<BiConsumer<Student, StudentDTO>> fieldUpdaters = List.of((s, dto) -> {
			if (dto.getFirstName() != null)
				s.setFirstName(dto.getFirstName());
		}, (s, dto) -> {
			if (dto.getLastName() != null)
				s.setLastName(dto.getLastName());
		}, (s, dto) -> {
			if (dto.getMobile() != null)
				s.setMobile(dto.getMobile());
		}, (s, dto) -> {
			if (dto.getImage() != null)
				s.setImage(dto.getImage());
		}, (s, dto) -> {
			if (dto.getEmail() != null)
				s.setEmail(dto.getEmail());
		}, (s, dto) -> {
			if (dto.getDob() != null)
				s.setDob(dto.getDob());
		}, (s, dto) -> {
			if (dto.getNoOfClassesAttended() != null)
				s.setNoOfClassesAttended(dto.getNoOfClassesAttended());
		}, (s, dto) -> {
			if (dto.getCurrentTopic() != null)
				s.setCurrentTopic(dto.getCurrentTopic());
		}, (s, dto) -> {
			if (dto.getDeviceToken() != null)
				s.setDeviceToken(dto.getDeviceToken());
		}, (s, dto) -> {
			if (dto.getAadharCardNumber() != null)
				s.setAadharCardNumber(dto.getAadharCardNumber());
		}, (s, dto) -> {
			if (dto.getCollegeOrCompany() != null)
				s.setCollegeOrCompany(dto.getCollegeOrCompany());
		}, (s, dto) -> {
			if (dto.getOccupation() != null)
				s.setOccupation(dto.getOccupation());
		}, (s, dto) -> {
			if (dto.getParentMobileNumber() != null)
				s.setParentMobileNumber(dto.getParentMobileNumber());
		}, (s, dto) -> {
			if (dto.getRegisteredDate() != null)
				s.setRegisteredDate(dto.getRegisteredDate());
		}, (s, dto) -> {
			if (dto.getQualification() != null)
				s.setQualification(dto.getQualification());
		}, (s, dto) -> {
			if (dto.getCertificateNumber() != null)
				s.setCertificateNumber(dto.getCertificateNumber());
		}, (s, dto) -> {
			if (dto.getPassword() != null)
				s.setPassword(dto.getPassword());
		}, (s, dto) -> {
			if (dto.getAddress() != null) {
				Address address = s.getAddress() != null ? s.getAddress() : new Address();
				address.setStreet(dto.getAddress().getStreet());
				address.setCity(dto.getAddress().getCity());
				address.setState(dto.getAddress().getState());
				address.setCountry(dto.getAddress().getCountry());
				address.setZipcode(dto.getAddress().getZipcode());
				s.setAddress(address);
			}
		}, (s, dto) -> {
			if (dto.getStatus() != null) {
				s.setStatus(dto.getStatus());
			}
		}, (s, dto) -> {
			if (dto.getBatch() != null) {
				Batch batch = s.getBatch() != null ? s.getBatch() : new Batch();
				batch.setId(dto.getBatch().getId());
				batch.setClassLink(dto.getBatch().getClassLink());
				batch.setClassLinkExpiry(dto.getBatch().getClassLinkExpiry());
				batch.setStartDate(dto.getBatch().getStartDate());
				batch.setEndDate(dto.getBatch().getEndDate());
				batch.setLanguage(dto.getBatch().getLanguage());
				batch.setIsActive(dto.getBatch().getIsActive());
				s.setBatch(batch);
			}
		}, (s, dto) -> {
			if (dto.getCourse() != null) {
				Course course = s.getCourse() != null ? s.getCourse() : new Course();
				course.setId(dto.getCourse().getId());
				course.setName(dto.getCourse().getName());
				course.setDescription(dto.getCourse().getDescription());
				course.setDuration(dto.getCourse().getDuration());
				course.setFees(dto.getCourse().getFees());
				s.setCourse(course);
			}
		});

		fieldUpdaters.forEach(updater -> updater.accept(existingStudent, studentDto));
	}
}
