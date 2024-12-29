package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.project.batch.entity.Address;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}