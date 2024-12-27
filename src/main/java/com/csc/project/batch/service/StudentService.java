package com.csc.project.batch.service;

import java.util.List;

import com.csc.project.batch.entity.Student;
import com.csc.project.common.exception.StudentNotFoundException;
import com.csc.project.request.StudentRegisterRequest;

public interface StudentService {

	String registerStudentFirstTime(StudentRegisterRequest student);

	void updateStudentDetails(StudentRegisterRequest student);

	void deleteStudent(String email) throws StudentNotFoundException;

	Student getStudentByEmail(String email) throws StudentNotFoundException;

	List<Student> getAllStudents();
}