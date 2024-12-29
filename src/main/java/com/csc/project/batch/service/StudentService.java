package com.csc.project.batch.service;

import java.util.List;

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Student;

public interface StudentService {

	String registerStudentFirstTime(StudentDTO student);

	void updateStudentDetails(StudentDTO student);

	void deleteStudent(String email) ;

	Student getStudentByEmail(String email) ;

	List<Student> getAllStudents();
}