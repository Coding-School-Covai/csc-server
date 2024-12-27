package com.csc.project.batch.entity;

import java.time.LocalDate;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50) // Database constraint
	@Size(max = 50) // Application-level validation
	private String firstName;

	@Column(length = 50)
	@Size(max = 50)
	private String lastName;

	@Column(length = 100, unique = true)
	@Size(max = 100)
	private String email;

	@Column(length = 100)
	private String password;

	private LocalDate doj;

	private LocalDate dob;

	@Column(length = 15)
	@Size(max = 15)
	private String mobile;

	@Column(length = 255)
	private String image;

	private Long noOfClassesAttended;

	@Column(length = 255)
	@Size(max = 255)
	private String currentTopic;

	@Column(length = 255)
	private String deviceToken;

	@Column(length = 12, unique = true)
	@Size(max = 12, min = 12) 
	private String aadharCardNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(length = 100)
	private String collegeOrCompany;

	@Column(length = 50)
	private String occupation;

	@Column(length = 15)
	@Size(max = 15)
	private String parentMobileNumber;

	private LocalDate registeredDate;

	@Column(length = 50)
	private String qualification;

	@Column(length = 20)
	private String certificateNumber;

	private LocalDate createdDate;

	private LocalDate updatedDate;

	private boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getNoOfClassesAttended() {
		return noOfClassesAttended;
	}

	public void setNoOfClassesAttended(Long noOfClassesAttended) {
		this.noOfClassesAttended = noOfClassesAttended;
	}

	public String getCurrentTopic() {
		return currentTopic;
	}

	public void setCurrentTopic(String currentTopic) {
		this.currentTopic = currentTopic;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCollegeOrCompany() {
		return collegeOrCompany;
	}

	public void setCollegeOrCompany(String collegeOrCompany) {
		this.collegeOrCompany = collegeOrCompany;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getParentMobileNumber() {
		return parentMobileNumber;
	}

	public void setParentMobileNumber(String parentMobileNumber) {
		this.parentMobileNumber = parentMobileNumber;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return getFirstName() + getLastName();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", doj=" + doj + ", dob=" + dob + ", mobile=" + mobile + ", image=" + image
				+ ", noOfClassesAttended=" + noOfClassesAttended + ", currentTopic=" + currentTopic + ", deviceToken="
				+ deviceToken + ", aadharCardNumber=" + aadharCardNumber + ", address=" + address
				+ ", collegeOrCompany=" + collegeOrCompany + ", occupation=" + occupation + ", parentMobileNumber="
				+ parentMobileNumber + ", registeredDate=" + registeredDate + ", qualification=" + qualification
				+ ", certificateNumber=" + certificateNumber + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", isActive=" + isActive + "]";
	}

}
