package com.csc.project.batch.entity;

import java.time.LocalDate;

import com.csc.project.common.jpa.audit.Auditable;

import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50) // Database constraint
	@Size(max = 50) // Application-level validation
	private String firstName;

	@Column(length = 50)
	@Size(max = 50)
	private String lastName;

	@Column(length = 50, unique = true)
	@Size(max = 50)
	@Column(length = 50, unique = true)
	@Size(max = 50)
	private String email;

	@Column(length = 20)
	@Column(length = 20)
	private String password;

	private LocalDate dob;

	@Column(length = 12)
	@Size(max = 12)
	@Column(length = 12)
	@Size(max = 12)
	private String mobile;

	@Column(length = 255)
	private String image;

	private Long noOfClassesAttended;

	@Column(length = 50)
	@Size(max = 50)
	@Column(length = 50)
	@Size(max = 50)
	private String currentTopic;

	@Column(length = 255)
	private String deviceToken;

	@Column(length = 12, unique = true)
	@Size(max = 12, min = 12)
	@Size(max = 12, min = 12)
	private String aadharCardNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(length = 50)
	private String collegeOrCompany;

	@Column(length = 50)
	private String occupation;

	@Column(length = 12)
	@Size(max = 12)
	@Column(length = 12)
	@Size(max = 12)
	private String parentMobileNumber;

	private LocalDate registeredDate;
	private LocalDate completedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")
	private Batch batch;
	
	@Column(length = 200)
	private String gitHub;

	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@Column(length = 20)
	@Column(length = 20)
	private String qualification;

	@Column(length = 20)
	private String certificateNumber;

	private boolean isActive;
}
