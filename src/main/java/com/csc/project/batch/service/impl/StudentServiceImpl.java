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

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Student;
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

    public StudentServiceImpl(StudentRepository studentRepository, JwtService jwtService, StudentMapper studentMapper, AddressMapper addressMapper) {
        this.studentRepository = studentRepository;
        this.jwtService = jwtService;
        this.studentMapper = studentMapper;
        this.addressMapper = addressMapper;
    }

    public String registerStudentFirstTime(StudentDTO studentDTO) {
        log.info("Adding student with data: {}", studentDTO);

        Student student = studentMapper.studentDtoToStudent(studentDTO);
        if (studentDTO.getAddress() != null) {
            student.setAddress(addressMapper.addressDtoToAddress(studentDTO.getAddress()));
        }
        student.setActive(true);
        studentRepository.save(student);
        log.info("Student added successfully.");
        return jwtService.generateToken(student.getEmail());
    }

    public void updateStudentDetails(StudentDTO studentDTO,String email) {
        log.info("Updating student details for email: {}", studentDTO.getEmail());

        Student existingStudent = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + studentDTO.getEmail()));

        studentMapper.updateStudentFromDto(studentDTO, existingStudent);

        if (studentDTO.getAddress() != null) {
            if (existingStudent.getAddress() == null) {
                existingStudent.setAddress(addressMapper.addressDtoToAddress(studentDTO.getAddress()));
            } else {
                addressMapper.updateAddressFromDto(studentDTO.getAddress(), existingStudent.getAddress());
            }
        }
        studentRepository.save(existingStudent);

        log.info("Student details updated successfully for email: {}", studentDTO.getEmail());
    }


    @Override
    public StudentDTO getStudentByEmail(String email) {
        log.info("Get student by email: {}", email);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student with email " + email + " not found"));
        return studentMapper.studentToStudentDTO(student);
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
     * @throws ResourceNotFoundException if the student is not found
     */
    @Override
    public void deleteStudent(String email) {
        log.info("delete student by email: {}", email);

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student with email " + email + " not found"));
        
        student.setActive(false);
        studentRepository.save(student);
        log.info("Student deleted successfully: {}", email);

    }
}
