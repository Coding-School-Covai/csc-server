package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.csc.project.batch.entity.Modules;

public interface ModuleRepository extends JpaRepository<Modules, Long>, JpaSpecificationExecutor<Modules>{

}
