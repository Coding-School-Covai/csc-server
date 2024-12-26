package com.csc.project.batch.dto;

import com.csc.project.common.dto.BaseFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallmentFilter extends BaseFilter {

    private Long courseId;  
    private Double minAmount; 
    private Double maxAmount; 
    private String status; 
}
