package com.csc.project.batch.entity;

import java.time.LocalDate;
import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "staffs")
@EqualsAndHashCode(callSuper = true)
public class Staff extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "doj")
	private LocalDate doj;

	@Column(name = "dob")
	private LocalDate dob;
	
	@Column(name = "mobileNo")
	private String mobileNo;
	
	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "permission")
	private String permission;
	
	@Column(name = "class_count")
	private Long classCount;

	@Column(name = "hour_salary")
	private Double hourSalary;

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "id_proof")
	private String idProofNumber;

	@Column(name = "contract_number")
	private String contractNumber;

	@Column(name = "isActive")
	private Boolean isActive;
	
}
