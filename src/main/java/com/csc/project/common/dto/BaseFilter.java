package com.csc.project.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFilter {
	
	private int limit;
	private int offset;
	private String order;
	private String orderBy;
}
