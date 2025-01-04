package com.csc.project.batch.service;

import java.util.List;

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Student;

public interface StudentService {

	String registerStudentFirstTime(StudentDTO student);

	void updateStudentDetails(StudentDTO student, String email);

	void deleteStudent(String email);

	StudentDTO getStudentByEmail(String email);

	List<Student> getAllStudents();
}