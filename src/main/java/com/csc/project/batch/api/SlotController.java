package com.csc.project.batch.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.project.batch.dto.SlotDTO;
import com.csc.project.batch.dto.SlotFilter;
import com.csc.project.batch.dto.SlotPageResponse;
import com.csc.project.batch.service.SlotService;
import com.csc.project.common.dto.AppResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/slots")
@Tag(name = "Slots API's", description = "API's for managing slots")
public class SlotController {
	
	private final SlotService slotservice;

	private static final String SLOT_CREATED_MESSAGE = "Slot created sucessfully";
	private static final String SLOT_UPDATED_MESSAGE = "Slot updated sucessfully";

	
	public SlotController(SlotService slotservice) {
		this.slotservice = slotservice;
	}
	
	@PostMapping
	@Operation(summary = "Add a new slot", description = "Create a new Slot", responses = {
			@ApiResponse(responseCode = "200", description = "Slot created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
	})
	public ResponseEntity<AppResponse<Object>> addSlot(@Valid @RequestBody SlotDTO slotDto){
		slotservice.addSlot(slotDto);
		return ResponseEntity.ok(AppResponse.builder().success(true).message(SLOT_CREATED_MESSAGE).build());
	}
	
    @GetMapping
    @Operation(summary = "Get slots", description = "Retrieves a paginated list of slots based on query parameters.", responses = {
            @ApiResponse(responseCode = "200", description = "A paginated list of slots", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotPageResponse.class)))
    })
    public ResponseEntity<SlotPageResponse> getSlots(@ModelAttribute SlotFilter slotFilter) {
    	SlotPageResponse slots = slotservice.getSlots(slotFilter);
        return ResponseEntity.ok(slots);
    }

    @GetMapping("/{slotId}")
    @Operation(summary = "Get an slot by ID", description = "Retrieves a single slot's details by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Slot details", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SlotDTO.class)))
    })
    public ResponseEntity<SlotDTO> getSlotById(@PathVariable long slotId) {
        SlotDTO slot = slotservice.getSlotById(slotId);
        return ResponseEntity.ok(slot);
    }

    @PutMapping("/{slotId}")
    @Operation(summary = "Update an slot", description = "Updates the details of an existing slot by its ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Slot updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppResponse.class)))
    })
    public ResponseEntity<AppResponse<Object>> updateAffiliate(@PathVariable long slotId,
            @Valid @RequestBody SlotDTO slotDto) {
    	slotservice.updateSlot(slotId, slotDto);
        return ResponseEntity.ok(AppResponse.builder().success(true).message(SLOT_UPDATED_MESSAGE).build());
    }

}
