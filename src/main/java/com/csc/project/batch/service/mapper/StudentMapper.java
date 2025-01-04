package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.csc.project.batch.dto.AddressDTO;
import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.dto.CourseDTO;
import com.csc.project.batch.dto.StudentDTO;
import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Status;
import com.csc.project.batch.entity.Student;
import com.csc.project.common.exception.ValidationException;

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
        student.setGitHub(studentDto.getGitHub());
        student.setParentMobileNumber(studentDto.getParentMobileNumber());
        student.setRegisteredDate(studentDto.getRegisteredDate());
        student.setNoOfClassesAttended(studentDto.getNoOfClassesAttended());
        student.setQualification(studentDto.getQualification());
        student.setCertificateNumber(studentDto.getCertificateNumber());
        student.setActive(studentDto.isActive());

        if (studentDto.getStatus() != null) {
            student.setStatus(studentDto.getStatus());
        }
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
        studentDTO.setGitHub(student.getGitHub());
        studentDTO.setCollegeOrCompany(student.getCollegeOrCompany());
        studentDTO.setOccupation(student.getOccupation());
        studentDTO.setParentMobileNumber(student.getParentMobileNumber());
        studentDTO.setRegisteredDate(student.getRegisteredDate());
        studentDTO.setNoOfClassesAttended(student.getNoOfClassesAttended());
        studentDTO.setQualification(student.getQualification());
        studentDTO.setCertificateNumber(student.getCertificateNumber());
        studentDTO.setActive(student.isActive());
        
        if (student.getStatus() != null) {
        	studentDTO.setStatus(student.getStatus());
        }

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
            batchDTO.setClassLink(student.getBatch().getClassLink());
            batchDTO.setClassLinkExpiry(student.getBatch().getClassLinkExpiry());
            batchDTO.setStartDate(student.getBatch().getStartDate());
            batchDTO.setEndDate(student.getBatch().getEndDate());
            batchDTO.setLanguage(student.getBatch().getLanguage());
            batchDTO.setIsActive(student.getBatch().getIsActive());

            if (student.getBatch().getSlot() != null) {
                batchDTO.setSlotId(student.getBatch().getSlot().getId());
                batchDTO.setSlotName(student.getBatch().getSlot().getName());
            }

            if (student.getBatch().getCourse() != null) {
                batchDTO.setCourseId(student.getBatch().getCourse().getId());
                batchDTO.setCourseName(student.getBatch().getCourse().getName());
            }

            studentDTO.setBatch(batchDTO);
        }
        if (student.getCourse() != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(student.getCourse().getId());
            courseDTO.setName(student.getCourse().getName());
            courseDTO.setDuration(student.getCourse().getDuration());
            courseDTO.setFees(student.getCourse().getFees());
            courseDTO.setCategory(student.getCourse().getCategory());
            courseDTO.setDescription(student.getCourse().getDescription());
            courseDTO.setLevel(student.getCourse().getLevel());
            courseDTO.setActive(student.getCourse().isActive());
//            courseDTO.setSubCourses(student.getCourse().getSubCourses());
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

        Optional.ofNullable(studentDto.getFirstName()).ifPresent(existingStudent::setFirstName);
        Optional.ofNullable(studentDto.getLastName()).ifPresent(existingStudent::setLastName);
        Optional.ofNullable(studentDto.getPassword()).ifPresent(existingStudent::setPassword);
        Optional.ofNullable(studentDto.getImage()).ifPresent(existingStudent::setImage);
        Optional.ofNullable(studentDto.getEmail())
                .filter(email -> !email.isEmpty()) 
                .ifPresent(existingStudent::setEmail);
        Optional.ofNullable(studentDto.getMobile()).ifPresent(existingStudent::setMobile);
        Optional.ofNullable(studentDto.getDob()).ifPresent(existingStudent::setDob);
        Optional.ofNullable(studentDto.getGitHub()).ifPresent(existingStudent::setGitHub);
        Optional.ofNullable(studentDto.getNoOfClassesAttended()).ifPresent(existingStudent::setNoOfClassesAttended);
        Optional.ofNullable(studentDto.getCurrentTopic()).ifPresent(existingStudent::setCurrentTopic);
        Optional.ofNullable(studentDto.getDeviceToken()).ifPresent(existingStudent::setDeviceToken);
        Optional.ofNullable(studentDto.getAadharCardNumber()).ifPresent(existingStudent::setAadharCardNumber);
        Optional.ofNullable(studentDto.getCollegeOrCompany()).ifPresent(existingStudent::setCollegeOrCompany);
        Optional.ofNullable(studentDto.getOccupation()).ifPresent(existingStudent::setOccupation);
        Optional.ofNullable(studentDto.getParentMobileNumber()).ifPresent(existingStudent::setParentMobileNumber);
        Optional.ofNullable(studentDto.getRegisteredDate()).ifPresent(existingStudent::setRegisteredDate);
        Optional.ofNullable(studentDto.getQualification()).ifPresent(existingStudent::setQualification);
        Optional.ofNullable(studentDto.getCertificateNumber()).ifPresent(existingStudent::setCertificateNumber);

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
            existingStudent.setStatus(studentDto.getStatus());
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
