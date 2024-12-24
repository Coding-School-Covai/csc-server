package com.csc.project.batch.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.csc.project.batch.dto.ModuleDTO;
import com.csc.project.batch.dto.ModuleFilter;
import com.csc.project.batch.dto.ModulePageResponse;
import com.csc.project.batch.service.ModuleService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/modules")
@Tag(name = "Modules API's", description = "API's for managing modules")
public class ModuleController {

    private final ModuleService moduleService;

    private static final String MODULE_CREATED_MESSAGE = "Module created successfully";
    private static final String MODULE_UPDATED_MESSAGE = "Module updated successfully";

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    @Operation(summary = "Add a new module", description = "Create a new Module", responses = {
            @ApiResponse(responseCode = "200", description = "Module created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> addModule(@Valid @RequestBody ModuleDTO moduleDto) {
        moduleService.addModule(moduleDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(MODULE_CREATED_MESSAGE).build());
    }

    @GetMapping
    @Operation(summary = "Get modules", description = "Retrieves a paginated list of modules based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of modules", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModulePageResponse.class)))
    })
    public ResponseEntity<ModulePageResponse> getModules(@ModelAttribute ModuleFilter moduleFilter) {
        ModulePageResponse modules = moduleService.getModules(moduleFilter);
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/{moduleId}")
    @Operation(summary = "Get a module by ID", description = "Retrieves a single module's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Module details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModuleDTO.class)))
    })
    public ResponseEntity<ModuleDTO> getModuleById(@PathVariable long moduleId) {
        ModuleDTO module = moduleService.getModuleById(moduleId);
        return ResponseEntity.ok(module);
    }

    @PutMapping("/{moduleId}")
    @Operation(summary = "Update a module", description = "Updates the details of an existing module by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Module updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateModule(@PathVariable long moduleId,
            @Valid @RequestBody ModuleDTO moduleDto) {
        moduleService.updateModule(moduleId, moduleDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(MODULE_UPDATED_MESSAGE).build());
    }
}
