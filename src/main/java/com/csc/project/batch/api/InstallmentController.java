package com.csc.project.batch.api;

import com.csc.project.batch.dto.InstallmentDTO;
import com.csc.project.batch.dto.InstallmentFilter;
import com.csc.project.batch.dto.InstallmentPageResponse;
import com.csc.project.batch.service.InstallmentService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/installments")
@Tag(name = "Installments API", description = "API for managing installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    private static final String INSTALLMENT_CREATED_MESSAGE = "Installment created successfully";
    private static final String INSTALLMENT_UPDATED_MESSAGE = "Installment updated successfully";

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping
    @Operation(summary = "Add a new installment", description = "Create a new installment", responses = {
            @ApiResponse(responseCode = "200", description = "Installment created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> addInstallment(@Valid @RequestBody InstallmentDTO installmentDTO) {
        installmentService.addInstallment(installmentDTO);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(INSTALLMENT_CREATED_MESSAGE).build());
    }

    @GetMapping
    @Operation(summary = "Get installments", description = "Retrieve a paginated list of installments", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of installments", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstallmentPageResponse.class)))
    })
    public ResponseEntity<InstallmentPageResponse> getInstallments(@ModelAttribute InstallmentFilter installmentFilter) {
        InstallmentPageResponse installments = installmentService.getInstallments(installmentFilter);
        return ResponseEntity.ok(installments);
    }

    @GetMapping("/{installmentId}")
    @Operation(summary = "Get an installment by ID", description = "Retrieve details of an installment by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Installment details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstallmentDTO.class)))
    })
    public ResponseEntity<InstallmentDTO> getInstallmentById(@PathVariable Long installmentId) {
        InstallmentDTO installment = installmentService.getInstallmentById(installmentId);
        return ResponseEntity.ok(installment);
    }

    @PutMapping("/{installmentId}")
    @Operation(summary = "Update an installment", description = "Update details of an existing installment by its ID", responses = {
            @ApiResponse(responseCode = "200", description = "Installment updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateInstallment(@PathVariable Long installmentId,
                                                                 @Valid @RequestBody InstallmentDTO installmentDTO) {
        installmentService.updateInstallment(installmentId, installmentDTO);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(INSTALLMENT_UPDATED_MESSAGE).build());
    }

   
}
