package com.csc.project.batch.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.dto.BatchFilter;
import com.csc.project.batch.dto.BatchPageResponse;
import com.csc.project.batch.service.BatchService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/batches")
@Tag(name = "Batches API's", description = "API's for managing batches")
public class BatchController {
	
	private final BatchService batchService;

	private static final String BATCH_CREATED_MESSAGE = "Batch created sucessfully";
	private static final String BATCH_UPDATED_MESSAGE = "Batch updated sucessfully";

	
	public BatchController(BatchService batchService) {
		this.batchService = batchService;
	}
	
	@PostMapping
	@Operation(summary = "Add a new batch", description = "Create a new Batch", responses = {
			@ApiResponse(responseCode = "200", description = "Batch created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
	})
	public ResponseEntity<AppResponse<Object>> addBatch(@Valid @RequestBody BatchDTO batchDto){
		batchService.addBatch(batchDto);
		return ResponseEntity.ok(AppResponse.builder().success(true).message(BATCH_CREATED_MESSAGE).build());
	}
	
    @GetMapping
    @Operation(summary = "Get batchs", description = "Retrieves a paginated list of batchs based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of batchs", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatchPageResponse.class)))
    })
    public ResponseEntity<BatchPageResponse> getBatchs(@ModelAttribute BatchFilter batchFilter) {
    	BatchPageResponse batchs = batchService.getBatchs(batchFilter);
        return ResponseEntity.ok(batchs);
    }

    @GetMapping("/{batchId}")
    @Operation(summary = "Get an batch by ID", description = "Retrieves a single batch's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Batch details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BatchDTO.class)))
    })
    public ResponseEntity<BatchDTO> getBatchById(@PathVariable long batchId) {
        BatchDTO batch = batchService.getBatchById(batchId);
        return ResponseEntity.ok(batch);
    }

    @PutMapping("/{batchId}")
    @Operation(summary = "Update an batch", description = "Updates the details of an existing batch by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Batch updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateAffiliate(@PathVariable long batchId,
            @Valid @RequestBody BatchDTO batchDto) {
    	batchService.updateBatch(batchId, batchDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(BATCH_UPDATED_MESSAGE).build());
    }

    @GetMapping("/{batchId}/join")
    public ResponseEntity<String> joinClass(@PathVariable Long batchId) {
        String classLink = batchService.getClassLink(batchId);
        return ResponseEntity.ok(classLink);
    }

}
