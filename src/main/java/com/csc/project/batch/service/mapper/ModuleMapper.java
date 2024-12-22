package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.ModuleDTO;
import com.csc.project.batch.entity.*;
import com.csc.project.batch.entity.Modules;


@Component
public class ModuleMapper {

	public static Modules moduleDtoToModule(ModuleDTO moduleDto) {
        if (moduleDto == null) {
            return null;
        }

        Modules module = new Modules();
        module.setId(moduleDto.getId());
        module.setCloudFilePath(moduleDto.getCloudFilePath());
        module.setDuration(moduleDto.getDuration());

        // Set the SubCourse object if it exists
        if (moduleDto.getSubCourseId() != null) {
            SubCourse subCourse = new SubCourse();
            subCourse.setId(moduleDto.getSubCourseId());
            module.setSubCourse(subCourse);
        }

        module.setTopics(moduleDto.getTopics());
        return module;
    }

    // Convert Module entity to ModuleDTO
    public ModuleDTO moduleToModuleDTO(Modules module) {
        if (module == null) {
            return null;
        }

        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setId(module.getId());
        moduleDTO.setCloudFilePath(module.getCloudFilePath());
        moduleDTO.setDuration(module.getDuration());

        // Set the SubCourse ID in the DTO
        if (module.getSubCourse() != null) {
            moduleDTO.setSubCourseId(module.getSubCourse().getId());
        }

        moduleDTO.setTopics(module.getTopics());
        return moduleDTO;
    }

    // Convert list of Module entities to list of ModuleDTOs
    public List<ModuleDTO> modulesToModuleDTOs(List<Modules> modules) {
        return modules.stream().map(this::moduleToModuleDTO).collect(Collectors.toList());
    }

    // Update existing Module entity from ModuleDTO
    public void updateModuleFromDto(ModuleDTO moduleDto, Modules existingModule) {
        existingModule.setCloudFilePath(moduleDto.getCloudFilePath());
        existingModule.setDuration(moduleDto.getDuration());

        // Update the SubCourse if provided
        if (moduleDto.getSubCourseId() != null) {
            SubCourse subCourse = new SubCourse();
            subCourse.setId(moduleDto.getSubCourseId());
            existingModule.setSubCourse(subCourse);
        }

        existingModule.setTopics(moduleDto.getTopics());
    }       
}
