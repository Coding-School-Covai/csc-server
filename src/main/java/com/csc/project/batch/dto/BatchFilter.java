package com.csc.project.batch.dto;

import com.csc.project.common.dto.BaseFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchFilter extends BaseFilter{
	private String searchInput;
}
