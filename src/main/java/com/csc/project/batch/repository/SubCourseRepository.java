package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.SubCourse;
@Repository
public interface SubCourseRepository extends JpaRepository<SubCourse, Long>, JpaSpecificationExecutor<SubCourse>{

}
