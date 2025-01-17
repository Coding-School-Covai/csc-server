package com.csc.project.batch.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.ModuleDTO;
import com.csc.project.batch.dto.ModuleFilter;
import com.csc.project.batch.dto.ModulePageResponse;
import com.csc.project.batch.jpa.spec.ModuleSpecification;
import com.csc.project.batch.repository.ModuleRepository;
import com.csc.project.batch.service.ModuleService;
import com.csc.project.batch.service.mapper.ModuleMapper;
import com.csc.project.common.exception.ResourceNotFoundException;
import com.csc.project.batch.entity.Modules;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

	private final ModuleRepository moduleRepository;
	private final ModuleMapper moduleMapper;

	@Override
	public void addModule(ModuleDTO moduleDto) {
		log.info("Add module with data: {}", moduleDto);
		Modules module = ModuleMapper.moduleDtoToModule(moduleDto);
		moduleRepository.save(module);
		log.info("Module added successfully");
	}

	@Override
	public ModulePageResponse getModules(ModuleFilter moduleFilter) {
		log.info("Get all modules with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
				moduleFilter.getOffset(), moduleFilter.getLimit(), moduleFilter.getOrder(), moduleFilter.getOrderBy(),
				moduleFilter.getSearchInput());

		Specification<Modules> specification = ModuleSpecification.buildSpecification(moduleFilter);

		Page<Modules> modulePage = moduleRepository.findAll(specification,
				PageRequest.of(moduleFilter.getOffset(), moduleFilter.getLimit(),
						Sort.by(Sort.Direction.fromString(moduleFilter.getOrder()), moduleFilter.getOrderBy())));

		List<ModuleDTO> moduleDTOs = moduleMapper.modulesToModuleDTOs(modulePage.getContent());
		log.info("Total modules found: {}", modulePage.getTotalElements());
		return new ModulePageResponse(modulePage.getTotalElements(), moduleDTOs);
	}

	@Override
	public ModuleDTO getModuleById(Long moduleId) {
		log.info("Get module by id: {}", moduleId);
		Modules module = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
		return moduleMapper.moduleToModuleDTO(module);
	}

	@Override
	public void updateModule(Long moduleId, @Valid ModuleDTO moduleDto) {
		log.info("Update module with data: {}", moduleDto);
		Modules existingModule = moduleRepository.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
		moduleMapper.updateModuleFromDto(moduleDto, existingModule);
		Modules updatedModule = moduleRepository.save(existingModule);
		log.info("Updated module with id: {}", updatedModule.getId());
	}
}
