package com.csc.project.batch.dto;

import com.csc.project.common.dto.BaseFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleFilter extends BaseFilter {
	private Long subCourseId;
	private Integer duration;
	private String searchInput;
}