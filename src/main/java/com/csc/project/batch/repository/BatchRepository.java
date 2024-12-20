package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.csc.project.batch.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>, JpaSpecificationExecutor<Batch>{
	
}
