package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.SubCourse;

public interface SubCourseRepository extends JpaRepository<SubCourse, Long>, JpaSpecificationExecutor<SubCourse>{

}
