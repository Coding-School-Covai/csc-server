package com.csc.project.batch.dto;

import java.time.LocalDate;
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
public class StaffDTO {

	private Long id;

	private String name;
	
	private String email;

	private LocalDate doj;

	private LocalDate dob;

	private String mobileNo;

	private String address1;

	private String address2;
	
	private String city;
	
	private String state;

	private String country;

	private String pincode;
	
	private String permission;

	private Long classCount;

	private Double hourSalary;

	private String qualification;

	private String idProofNumber;

	private String contractNumber;

	private Boolean isActive;

}
