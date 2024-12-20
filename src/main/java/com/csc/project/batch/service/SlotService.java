package com.csc.project.batch.service;

import com.csc.project.batch.dto.SlotDTO;
import com.csc.project.batch.dto.SlotFilter;
import com.csc.project.batch.dto.SlotPageResponse;

import jakarta.validation.Valid;

public interface SlotService {

	void addSlot(SlotDTO slotDto);

	SlotPageResponse getSlots(SlotFilter slotFilter);

	SlotDTO getSlotById(long slotId);

	void updateSlot(long slotId, @Valid SlotDTO slotDto);
}
