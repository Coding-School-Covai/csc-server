package com.csc.project.batch.dto;

import java.time.LocalDate;

import com.csc.project.batch.entity.Address;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentDTO {

	private Long id;  
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private LocalDate dob;  
    private String mobile;
    private Long noOfClassesAttended;
    private String currentTopic;
    private String deviceToken;
    private String aadharCardNumber;
    private String collegeOrCompany;
    private String occupation;
    private String parentMobileNumber;
    private LocalDate registeredDate;
    private LocalDate completedDate;  
    private String qualification;
    private String certificateNumber;
    private boolean isActive;

    private Address address;
    private Batch batch;
    private Course course;
    private Status status;  


}
