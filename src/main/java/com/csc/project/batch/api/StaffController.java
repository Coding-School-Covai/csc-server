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

import com.csc.project.batch.dto.StaffDTO;
import com.csc.project.batch.dto.StaffFilter;
import com.csc.project.batch.dto.StaffPageResponse;
import com.csc.project.batch.service.StaffService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/staffs")
@Tag(name = "Staffs API's", description = "API's for managing staffs")
public class StaffController {
	
	private final StaffService staffService;

	private static final String STAFF_CREATED_MESSAGE = "Staff details created sucessfully";
	private static final String STAFF_UPDATED_MESSAGE = "Staff details updated sucessfully";

	
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@PostMapping
	@Operation(summary = "Add a new staff", description = "Create a new Staff", responses = {
			@ApiResponse(responseCode = "200", description = "Staff created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
	})
	public ResponseEntity<AppResponse<Object>> addStaff(@Valid @RequestBody StaffDTO staffDto){
		staffService.addStaff(staffDto);
		return ResponseEntity.ok(AppResponse.builder().success(true).message(STAFF_CREATED_MESSAGE).build());
	}
	
    @GetMapping
    @Operation(summary = "Get staffs", description = "Retrieves a paginated list of staffs based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of staffs", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StaffPageResponse.class)))
    })
    public ResponseEntity<StaffPageResponse> getStaffs(@ModelAttribute StaffFilter staffFilter) {
    	StaffPageResponse staffs = staffService.getStaffs(staffFilter);
        return ResponseEntity.ok(staffs);
    }

    @GetMapping("/{staffId}")
    @Operation(summary = "Get an staff by ID", description = "Retrieves a single staff's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Staff details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StaffDTO.class)))
    })
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable long staffId) {
        StaffDTO staff = staffService.getStaffById(staffId);
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{staffId}")
    @Operation(summary = "Update an staff", description = "Updates the details of an existing staff by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Staff updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateAffiliate(@PathVariable long staffId,
            @Valid @RequestBody StaffDTO staffDto) {
    	staffService.updateStaff(staffId, staffDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(STAFF_UPDATED_MESSAGE).build());
    }
}
