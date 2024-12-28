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
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Student;
import com.csc.project.batch.repository.AddressRepository;
import com.csc.project.batch.repository.BatchRepository;
import com.csc.project.batch.repository.CourseRepository;
import com.csc.project.batch.repository.StudentRepository;
import com.csc.project.batch.service.StudentService;
import com.csc.project.batch.service.mapper.AddressMapper;
import com.csc.project.batch.service.mapper.StudentMapper;
import com.csc.project.common.exception.ResourceNotFoundException;
import com.csc.project.security.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	private final JwtService jwtService;
	private final StudentMapper studentMapper;
	private final AddressMapper addressMapper;
	private final AddressRepository addressRepository;
	private final BatchRepository batchRepository;
	private final CourseRepository courseRepository;

	public StudentServiceImpl(StudentRepository studentRepository, JwtService jwtService, StudentMapper studentMapper,
			AddressMapper addressMapper, AddressRepository addressRepository, BatchRepository batchRepository,
			CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.jwtService = jwtService;
		this.studentMapper = studentMapper;
		this.addressMapper = addressMapper;
		this.addressRepository = addressRepository;
		this.batchRepository = batchRepository;
		this.courseRepository = courseRepository;
	}

	public String registerStudentFirstTime(StudentDTO studentDTO) {
		log.info("Adding student with data: {}", studentDTO);

		Student student = studentMapper.studentDtoToStudent(studentDTO);
		student.setActive(true);
		studentRepository.save(student);
		log.info("Student added successfully.");
		return jwtService.generateToken(student.getEmail());
	}

	public void updateStudentDetails(StudentDTO studentDTO, String email) {
		log.info("Get student with email: {}", studentDTO.getEmail());

		Student existingStudent = studentRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Student not found with email: " + studentDTO.getEmail()));
		studentMapper.updateStudentFromDto(studentDTO, existingStudent);
		Address address = existingStudent.getAddress();
		if (address != null && address.getId() == null) {
			addressRepository.save(address);
		}
		Batch batch = existingStudent.getBatch();
		if (batch != null && batch.getId() == null) {
			batchRepository.save(batch);
		}
		Course course = existingStudent.getCourse();
		if (course != null && course.getId() == null) {
			courseRepository.save(course);
		}
		studentRepository.save(existingStudent);
		log.info("Student updated successfully.");
	}

	@Override
	public StudentDTO getStudentByEmail(String email) {

		Student existingStudent = studentRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
		return studentMapper.studentToStudentDTO(existingStudent);
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
	public void deleteStudent(String email) {
		log.info("delete student by email: {}", email);
		Student existingStudent = studentRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
		existingStudent.setActive(false);
		studentRepository.save(existingStudent);
		log.info("Student deleted successfully.");
	}

	public Student getStudentDTOByEmail(String email) {
		log.info("Get student DTO by email: {}", email);
		StudentDTO studentDto = getStudentByEmail(email);
		return studentMapper.studentDtoToStudent(studentDto);
	}

	public List<StudentDTO> getAllStudentDTOs() {
		log.info("Getting all student DTOs");
		List<Student> students = getAllStudents();
		return studentMapper.studentsToStudentDTOs(students);
	}
}
