package com.csc.project.batch.api;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.csc.project.batch.entity.Student;
import com.csc.project.batch.service.StudentService;
import com.csc.project.batch.service.mapper.StudentMapper;
import com.csc.project.common.exception.StudentNotFoundException;
import com.csc.project.common.exception.UnauthorizedException;
import com.csc.project.common.util.ValidationUtils;
import com.csc.project.request.StudentRegisterRequest;
import com.csc.project.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	private static final Logger logger = LogManager.getLogger(StudentController.class);

	private final StudentService studentService;
	private final StudentMapper studentMapper;
	private final JwtService jwtService;
	private final ValidationUtils validationUtils;

	public StudentController(StudentService studentService, StudentMapper studentMapper, JwtService jwtService,
			ValidationUtils validationUtils) {
		this.studentService = studentService;
		this.studentMapper = studentMapper;
		this.jwtService = jwtService;
		this.validationUtils = validationUtils;
	}

	/**
	 * Registers a student or updates their details based on token presence.
	 * 
	 * @param studentDTO Student registration details
	 * @param token      Authorization token
	 * @return ResponseEntity containing the JWT or success message
	 */
	@PostMapping()
	public ResponseEntity<String> registerStudent(@RequestBody StudentRegisterRequest studentDTO,
			@RequestHeader(value = "Authorization", required = false) String token) {
		logger.info("Request student with details: {}", studentDTO.toString());
		logger.info("Token: {}", token);

		if (token == null || token.isEmpty()) {
			String jwtToken = studentService.registerStudentFirstTime(studentDTO);
			return ResponseEntity.ok(jwtToken);
		} else {
			String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
			logger.info("token - email: {}", studentDTO.getEmail());

			if (!jwtService.validateToken(jwt, studentDTO.getEmail())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
			}

			studentService.updateStudentDetails(studentDTO);
			return ResponseEntity.ok("Student details updated successfully");
		}
	}

	/**
	 * Updates a student's details.
	 * 
	 * @param studentDTO Student details to update
	 * @param token      Authorization token
	 * @return ResponseEntity containing success message
	 */
	@PutMapping()
	public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentRegisterRequest studentDTO,
			@RequestHeader(value = "Authorization", required = true) String token) {
		try {
			String email = validationUtils.tokenValidate(token);
			studentDTO.setEmail(email);
			studentService.updateStudentDetails(studentDTO);
			return ResponseEntity.ok("Student details updated successfully");
		} catch (UnauthorizedException e) {
			logger.error("Unauthorized access: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (Exception e) {
			logger.error("Error updating student: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	/**
	 * Deletes a student by ID.
	 * 
	 * 
	 * @return ResponseEntity containing success message
	 */
	@PostMapping("/delete")
	public ResponseEntity<String> deleteStudent(@RequestHeader(value = "Authorization", required = true) String token) {
		try {
			String email = validationUtils.tokenValidate(token);
			studentService.deleteStudent(email);
			return ResponseEntity.ok("Student deleted successfully");
		} catch (StudentNotFoundException e) {
			logger.error("Student not found: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			logger.error("Error deleting student: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	/**
	 * Fetches the details of a student based on the token.
	 * 
	 * @param token Authorization token
	 * @return ResponseEntity containing the student details
	 */
	@GetMapping()
	public ResponseEntity<?> getStudentByToken(@RequestHeader(value = "Authorization", required = true) String token) {
		try {
			String email = validationUtils.tokenValidate(token);
			logger.info("Fetching student details for email: {}", email);
			Student student = studentService.getStudentByEmail(email);
			return ResponseEntity.ok(studentMapper.toDto(student));
		} catch (StudentNotFoundException e) {
			logger.error("Student not found: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (UnauthorizedException e) {
			logger.error("Unauthorized access: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (Exception e) {
			logger.error("Error fetching student details: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

	/**
	 * Fetches all students.
	 * 
	 * @return ResponseEntity containing the list of all students
	 */
	@GetMapping("/all_details")
	public ResponseEntity<List<StudentRegisterRequest>> getAllStudents() {
		try {
			logger.info("Fetching all students");
			List<Student> students = studentService.getAllStudents();
			return ResponseEntity.ok(studentMapper.toDtoList(students));
		} catch (Exception e) {
			logger.error("Error fetching all students: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
