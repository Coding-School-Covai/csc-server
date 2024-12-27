package com.csc.project.batch.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Student;
import com.csc.project.batch.repository.StudentRepository;
import com.csc.project.batch.service.StudentService;
import com.csc.project.batch.service.mapper.StudentMapper;
import com.csc.project.common.exception.StudentNotFoundException;
import com.csc.project.request.StudentRegisterRequest;
import com.csc.project.security.JwtService;


@Service
public class ServiceImpl implements StudentService {

	private static final Logger logger = LogManager.getLogger(ServiceImpl.class);

	private final StudentRepository studentRepository;
	private final JwtService jwtService;
	private final StudentMapper studentMapper;

	public ServiceImpl(StudentRepository studentRepository, JwtService jwtService, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.jwtService = jwtService;
		this.studentMapper = studentMapper;
	}

	public String registerStudentFirstTime(StudentRegisterRequest studentReq) {

		Student student = new Student();
		updateStudentFields(student, studentReq);
		student.setCreatedDate(LocalDate.now()); 
		student.setUpdatedDate(LocalDate.now());
		student.setActive(true);
		studentRepository.save(student);

		return jwtService.generateToken(student.getEmail());
	}

	public void updateStudentDetails(StudentRegisterRequest studentDTO) {
		Optional<Student> existingStudent = Optional.ofNullable(studentRepository.findByEmail(studentDTO.getEmail()));
		if (existingStudent.isEmpty()) {
			throw new IllegalStateException("Student not found");
		}

		Student student = existingStudent.get();
		updateStudentFields(student, studentDTO);
		student.setUpdatedDate(LocalDate.now());
		studentRepository.save(student);
	}

	private void updateStudentFields(Student student, StudentRegisterRequest studentReq) {
		List<BiConsumer<Student, StudentRegisterRequest>> fieldUpdaters = Arrays.asList((s, dto) -> {
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
			if (dto.getDoj() != null)
				s.setDoj(dto.getDoj());
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
		},(s, dto) -> {
			if (dto.getPassword() != null)
				s.setPassword(dto.getPassword());
		}
				);
		

		fieldUpdaters.stream().forEach(updater -> updater.accept(student, studentReq));

		if (studentReq.getAddress() != null) {
			Address address = new Address();
			address.setId(studentReq.getAddress().getId());
			address.setStreet(studentReq.getAddress().getStreet());
			address.setCity(studentReq.getAddress().getCity());
			address.setState(studentReq.getAddress().getState());
			address.setCountry(studentReq.getAddress().getCountry());
			address.setZipcode(studentReq.getAddress().getZipcode());
			student.setAddress(address);
		}
	}

	public Student getStudentByEmail(String email) throws StudentNotFoundException {
		Student student = studentRepository.findByEmail(email);
		if (student == null) {
			throw new StudentNotFoundException("Student with email " + email + " not found");
		}
		if (student.getAddress() != null) {
	        student.getAddress().getId();
	        student.getAddress().getStreet();
	        student.getAddress().getCity();
	        student.getAddress().getState();
	        student.getAddress().getCountry();
	        student.getAddress().getZipcode();
	    }
		System.out.println(student+"------");
		return student;
	}

	/**
	 * Get all students.
	 *
	 * @return a list of all Student entities
	 */
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Delete a student by ID.
	 *
	 * @param id the ID of the student to delete
	 * @throws StudentNotFoundException if the student is not found
	 */
	public void deleteStudent(String email) throws StudentNotFoundException {
		if (studentRepository.findByEmail(email) != null) {
			Student student = studentRepository.findByEmail(email);
			student.setActive(false);
			studentRepository.save(student);
		} else {
			throw new StudentNotFoundException("Student with ID " + email + " not found");
		}
	}

}