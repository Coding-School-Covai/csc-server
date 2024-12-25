package com.csc.project.batch.dto;

import java.util.List;

import com.csc.project.batch.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursePageResponse {
	private Long totalRecords;
	private List<Course> data;
}
