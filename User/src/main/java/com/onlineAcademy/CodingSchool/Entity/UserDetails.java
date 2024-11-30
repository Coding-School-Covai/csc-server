package com.onlineAcademy.CodingSchool.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "doj") // Date of Joining
    private LocalDate doj;

    @Column(name = "dob") // Date of Birth
    private LocalDate dob;

    @Column(name = "mobile", length = 10, unique = true)
    private String mobile;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "no_of_classes_attended")
    private Long noOfClassesAttended;

    @Column(name = "current_topic")
    private String currentTopic;

    @Column(name = "device_token", unique = true)
    private String deviceToken;

    @Column(name = "aadhar_card_number", unique = true, length = 12)
    private String aadharCardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "college_or_company")
    private String collegeOrCompany;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "parent_mobile_number", length = 10)
    private String parentMobileNumber;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "batch_id")
//    private Batch batch;

    @Column(name = "qualification")
    private String qualification;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "status_id")
//    private Status status;

    @Column(name = "certificate_number", unique = true)
    private String certificateNumber;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "is_active")
    private boolean isActive;

}

