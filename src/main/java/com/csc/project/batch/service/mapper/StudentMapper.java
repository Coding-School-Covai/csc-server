package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Student;
import com.csc.project.common.exception.ValidationException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Student;
import com.csc.project.batch.entity.Status;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
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

        // Status mapping
        student.setStatus(Status.valueOf(studentDto.getStatus() != null ? studentDto.getStatus() : "NOT_VERIFIED"));

        // Address mapping
        if (studentDto.getAddress() != null) {
            student.setAddress(studentDto.getAddress());
        }

        // Batch and Course mapping
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

        studentDTO.setStatus(student.getStatus() != null ? student.getStatus().name() : Status.NOT_VERIFIED.name());

        if (student.getAddress() != null) {
            studentDTO.setAddress(student.getAddress());
        }

        if (student.getBatch() != null) {
            studentDTO.setBatch(student.getBatch());
        }

        if (student.getCourse() != null) {
            studentDTO.setCourse(student.getCourse());
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

        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setMobile(studentDto.getMobile());
        existingStudent.setImage(studentDto.getImage());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setDob(studentDto.getDob());
        existingStudent.setDoj(studentDto.getDoj());
        existingStudent.setNoOfClassesAttended(studentDto.getNoOfClassesAttended());
        existingStudent.setCurrentTopic(studentDto.getCurrentTopic());
        existingStudent.setDeviceToken(studentDto.getDeviceToken());
        existingStudent.setAadharCardNumber(studentDto.getAadharCardNumber());
        existingStudent.setCollegeOrCompany(studentDto.getCollegeOrCompany());
        existingStudent.setOccupation(studentDto.getOccupation());
        existingStudent.setParentMobileNumber(studentDto.getParentMobileNumber());
        existingStudent.setRegisteredDate(studentDto.getRegisteredDate());
        existingStudent.setQualification(studentDto.getQualification());
        existingStudent.setCertificateNumber(studentDto.getCertificateNumber());
        existingStudent.setPassword(studentDto.getPassword());

        if (studentDto.getAddress() != null) {
            Address address = existingStudent.getAddress() != null ? existingStudent.getAddress() : new Address();
            address.setStreet(studentDto.getAddress().getStreet());
            address.setCity(studentDto.getAddress().getCity());
            address.setState(studentDto.getAddress().getState());
            address.setCountry(studentDto.getAddress().getCountry());
            address.setZipcode(studentDto.getAddress().getZipcode());
            existingStudent.setAddress(address);
        }

        if (studentDto.getStatus() != null) {
            existingStudent.setStatus(Status.valueOf(studentDto.getStatus()));
        }

        if (studentDto.getBatch() != null && studentDto.getBatch().getId() != null) {
            Batch batch = new Batch();
            batch.setId(studentDto.getBatch().getId());
            existingStudent.setBatch(batch);
        }

        if (studentDto.getCourse() != null && studentDto.getCourse().getId() != null) {
            Course course = new Course();
            course.setId(studentDto.getCourse().getId());
            existingStudent.setCourse(course);
        }
    }

}
