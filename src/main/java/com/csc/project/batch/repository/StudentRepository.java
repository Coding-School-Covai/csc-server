package com.csc.project.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.project.batch.entity.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);

}