package com.onlineAcademy.CodingSchool.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineAcademy.CodingSchool.Entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
