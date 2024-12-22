package com.csc.project.batch.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulePageResponse {
    private Long totalRecords; 
    private List<ModuleDTO> data; 
}