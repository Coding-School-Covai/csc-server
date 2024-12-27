package com.csc.project.request;

import java.time.LocalDate;

import com.csc.project.batch.entity.Address;


public class StudentRegisterRequest {

	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private LocalDate doj;
    private LocalDate dob;
    private String mobile;
//    private String role;
//    private String refreshToken;
    private Long noOfClassesAttended;
    private String currentTopic;
    private String deviceToken;
    private String aadharCardNumber;
    private String collegeOrCompany;
    private String occupation;
    private String parentMobileNumber;
    private LocalDate registeredDate;
    private String qualification;
    private String certificateNumber;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private boolean isActive;
    
    private Address address;
    
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
//	public String getRefreshToken() {
//		return refreshToken;
//	}
//	public void setRefreshToken(String refreshToken) {
//		this.refreshToken = refreshToken;
//	}
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "StudentRegisterRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", image=" + image + ", doj=" + doj + ", dob=" + dob + ", mobile=" + mobile
				+ ", noOfClassesAttended=" + noOfClassesAttended + ", currentTopic=" + currentTopic + ", deviceToken="
				+ deviceToken + ", aadharCardNumber=" + aadharCardNumber + ", collegeOrCompany=" + collegeOrCompany
				+ ", occupation=" + occupation + ", parentMobileNumber=" + parentMobileNumber + ", registeredDate="
				+ registeredDate + ", qualification=" + qualification + ", certificateNumber=" + certificateNumber
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", isActive=" + isActive
				+ ", address=" + address + "]";
	}
    
}
