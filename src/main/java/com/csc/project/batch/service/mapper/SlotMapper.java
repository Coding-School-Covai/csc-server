package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.SlotDTO;
import com.csc.project.batch.entity.Slot;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SlotMapper {

	public static Slot slotDtoToSlot(SlotDTO slotDto) {
	    if (slotDto == null) {
	        return null;
	    }

		Slot slot = new Slot();
		slot.setId(slotDto.getId());
		slot.setName(slotDto.getName());
		slot.setDuration(slotDto.getDuration());
		slot.setStartTime(slotDto.getStartTime());
		slot.setEndTime(slotDto.getEndTime());
		slot.setIsActive(slotDto.getIsActive());
		return slot;
	}

	public SlotDTO slotToSlotDTO(Slot slot) {
		    if (slot == null) {
		        return null;
		    }
		    
		    SlotDTO slotDTO = new SlotDTO();
		    slotDTO.setId(slot.getId());
		    slotDTO.setName(slot.getName());
		    slotDTO.setDuration(slot.getDuration());
		    slotDTO.setStartTime(slot.getStartTime());
		    slotDTO.setEndTime(slot.getEndTime());
		    slotDTO.setIsActive(slot.getIsActive());
		    return slotDTO;
		}

	public List<SlotDTO> slotsToSlotDTOs(List<Slot> slots) {
		return slots.stream().map(this::slotToSlotDTO).collect(Collectors.toList());
	}

	public void updateSlotFromDto(@Valid SlotDTO slotDto, Slot existingSlot) {

		existingSlot.setId(slotDto.getId());
		existingSlot.setName(slotDto.getName());
		existingSlot.setDuration(slotDto.getDuration());
		existingSlot.setStartTime(slotDto.getStartTime());
		existingSlot.setEndTime(slotDto.getEndTime());
		existingSlot.setIsActive(slotDto.getIsActive());
	}

}
