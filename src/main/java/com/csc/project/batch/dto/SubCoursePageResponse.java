package com.csc.project.batch.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCoursePageResponse {
    private Long totalRecords;
    private List<SubCourseDTO> data;
}
