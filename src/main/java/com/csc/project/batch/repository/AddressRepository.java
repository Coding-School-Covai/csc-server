package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.project.batch.entity.Address;



public interface AddressRepository extends JpaRepository<Address, Long> {
}