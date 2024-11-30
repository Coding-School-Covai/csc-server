package com.onlineAcademy.CodingSchool.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineAcademy.CodingSchool.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

