package com.csc.project.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import com.csc.project.batch.entity.Student;
import com.csc.project.common.util.ValidationUtils;


public class StudentBuilder {

    private final Student student;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentBuilder() {
        this.student = new Student();
    }

    public StudentBuilder withFirstName(String firstName) {
    	 ValidationUtils.requireNonBlank("firstName", firstName);
        student.setFirstName(trimToNull(firstName));
        return this;
    }

    public StudentBuilder withLastName(String lastName) {
    	 ValidationUtils.requireNonBlank("lastName", lastName);
        student.setLastName(trimToNull(lastName));
        return this;
    }

    public StudentBuilder withEmail(String email) {
    	ValidationUtils.requireNonBlank("email", email);
        student.setEmail(trimToNull(email));
        return this;
    }

    public StudentBuilder withPassword(String password) {
    	ValidationUtils.requireNonBlank("password", password);
        student.setPassword(trimToNull(password));
        return this;
    }

    public StudentBuilder withDoj(LocalDate doj) {
    	ValidationUtils.requireNonBlank("doj", doj);
        student.setDoj(doj);
        return this;
    }

    public StudentBuilder withDob(LocalDate dob) {
    	ValidationUtils.requireNonBlank("dob", dob);
        student.setDob(dob);
        return this;
    }

    public StudentBuilder withMobile(String mobile) {
    	ValidationUtils.requireNonBlank("mobile", mobile);
        student.setMobile(trimToNull(mobile));
        return this;
    }

    public StudentBuilder withImage(String image) {
    	ValidationUtils.requireNonBlank("image", image);
        student.setImage(trimToNull(image));
        return this;
    }

    public StudentBuilder withCurrentTopic(String currentTopic) {
        student.setCurrentTopic(trimToNull(currentTopic));
        return this;
    }

    public StudentBuilder withDeviceToken(String deviceToken) {
        student.setDeviceToken(trimToNull(deviceToken));
        return this;
    }

    public StudentBuilder withAadharCardNumber(String aadharCardNumber) {
    	ValidationUtils.requireNonBlank("aadharCardNumber", aadharCardNumber);
        student.setAadharCardNumber(trimToNull(aadharCardNumber));
        return this;
    }

   

    public StudentBuilder withCollegeOrCompany(String collegeOrCompany) {
        student.setCollegeOrCompany(trimToNull(collegeOrCompany));
        return this;
    }

    public StudentBuilder withOccupation(String occupation) {
        student.setOccupation(trimToNull(occupation));
        return this;
    }

    public StudentBuilder withParentMobileNumber(String parentMobileNumber) {
        student.setParentMobileNumber(trimToNull(parentMobileNumber));
        return this;
    }

    public StudentBuilder withRegisteredDate(LocalDate localDate) {
        student.setRegisteredDate(localDate);
        return this;
    }

    public StudentBuilder withQualification(String qualification) {
        student.setQualification(trimToNull(qualification));
        return this;
    }

    public StudentBuilder withCertificateNumber(String certificateNumber) {
        student.setCertificateNumber(trimToNull(certificateNumber));
        return this;
    }

    public StudentBuilder withCreatedDate(LocalDate localDate) {
        student.setCreatedDate(localDate);
        return this;
    }

    public StudentBuilder withUpdatedDate(LocalDate updatedDate) {
        student.setUpdatedDate(updatedDate);
        return this;
    }

    public StudentBuilder withActive(boolean isActive) {
        student.setActive(isActive);
        return this;
    }

    public Student build() {
        return student;
    }

    // Utility methods
    private String trimToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value.trim();
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private LocalDate parseDateSafely(String dateStr) {
        return isNotBlank(dateStr) ? LocalDate.parse(dateStr.trim(), formatter) : null;
    }
}

