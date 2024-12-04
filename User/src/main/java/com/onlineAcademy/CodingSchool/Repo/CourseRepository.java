package com.onlineAcademy.CodingSchool.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineAcademy.CodingSchool.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	 Optional<Course> findByName(String name);
}
