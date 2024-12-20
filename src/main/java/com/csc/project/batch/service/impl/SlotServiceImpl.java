package com.csc.project.batch.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.SlotDTO;
import com.csc.project.batch.dto.SlotFilter;
import com.csc.project.batch.dto.SlotPageResponse;
import com.csc.project.batch.entity.Slot;
import com.csc.project.batch.jpa.spec.SlotSpecification;
import com.csc.project.batch.repository.SlotRepository;
import com.csc.project.batch.service.SlotService;
import com.csc.project.batch.service.mapper.SlotMapper;
import com.csc.project.common.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class SlotServiceImpl implements SlotService{
	
	private final SlotRepository slotRepository;
	private final SlotMapper slotMapper;
	
	@Override
	public void addSlot(SlotDTO slotDto) {
		log.info("Add slot with data:{}",slotDto);
		Slot slot = SlotMapper.slotDtoToSlot(slotDto);
		slotRepository.save(slot);
		log.info("Slot added successfully");	
	}

	@Override
	public SlotPageResponse getSlots(SlotFilter slotFilter) {
		log.info(
				"Get all slots with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
				slotFilter.getOffset(), slotFilter.getLimit(), slotFilter.getOrder(),
				slotFilter.getOrderBy(), slotFilter.getSearchInput());
		Specification<Slot> specification = SlotSpecification.buildSpecification(slotFilter);

		Page<Slot> slotPage = slotRepository.findAll(specification,
				PageRequest.of(slotFilter.getOffset(), slotFilter.getLimit(),
						Sort.by(Sort.Direction.fromString(slotFilter.getOrder()), slotFilter.getOrderBy())));

		List<SlotDTO> slotDTOs = slotMapper.slotsToSlotDTOs(slotPage.getContent());
		log.info("Total affiliates found: {}", slotPage.getTotalElements());
		return new SlotPageResponse(slotPage.getTotalElements(), slotDTOs);
	}

	@Override
	public SlotDTO getSlotById(long slotId) {
		log.info("Get slot by id: {}", slotId);
		Slot slot = slotRepository.findById(slotId)
				.orElseThrow(() -> new ResourceNotFoundException("Slot not found with id: " + slotId));
		return slotMapper.slotToSlotDTO(slot);
	}

	@Override
	public void updateSlot(long slotId, @Valid SlotDTO slotDto) {
		log.info("Update slot with data: {}", slotDto);
		Slot existingSlot = slotRepository.findById(slotId)
				.orElseThrow(() -> new ResourceNotFoundException("Slot not found with id: " + slotId));
		slotMapper.updateSlotFromDto(slotDto, existingSlot);
		Slot updatedSlot = slotRepository.save(existingSlot);
		log.info("Updated slot with id: {}", updatedSlot.getId());
	}
}
