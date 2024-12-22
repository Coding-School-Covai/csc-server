package com.csc.project.batch.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDTO {
	 private Long id;
	    private String name;
	    private double duration;
	    private double fees;
	    private String category;
	    private String description;
	    private String level;
	    private boolean isActive;
}
