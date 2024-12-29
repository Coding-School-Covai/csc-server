package com.csc.project.batch.service;

import com.csc.project.batch.dto.ModuleDTO;
import com.csc.project.batch.dto.ModuleFilter;
import com.csc.project.batch.dto.ModulePageResponse;

import jakarta.validation.Valid;

public interface ModuleService {
	void addModule(@Valid ModuleDTO moduleDTO);

    ModulePageResponse getModules(ModuleFilter moduleFilter);

    ModuleDTO getModuleById(Long moduleId);

    void updateModule(Long moduleId, @Valid ModuleDTO moduleDTO);

    void deleteModule(Long moduleId);
}
