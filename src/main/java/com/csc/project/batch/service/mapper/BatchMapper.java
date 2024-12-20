package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.entity.Batch;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BatchMapper {

	public static Batch batchDtoToBatch(BatchDTO batchDto) {
	    if (batchDto == null) {
	        return null;
	    }

		Batch batch = new Batch();
		batch.setId(batchDto.getId());
		batch.setClassLink(batchDto.getClassLink());;
		batch.setStartDate(batchDto.getStartDate());
		batch.setEndDate(batchDto.getEndDate());
		batch.setLanguage(batchDto.getLanguage());
		batch.setIsActive(batchDto.getIsActive());
		return batch;
	}

	public BatchDTO batchToBatchDTO(Batch batch) {
		    if (batch == null) {
		        return null;
		    }
		    
		    BatchDTO batchDTO = new BatchDTO();
		    batchDTO.setId(batch.getId());
		    batchDTO.setClassLink(batch.getClassLink());
		    batchDTO.setLanguage(batch.getLanguage());
		    batchDTO.setStartDate(batch.getStartDate());
		    batchDTO.setEndDate(batch.getEndDate());
		    batchDTO.setIsActive(batch.getIsActive());
			if (batch.getSlot() != null) {
				batchDTO.setSlotId(batch.getSlot().getId());
				batchDTO.setSlotName(batch.getSlot().getName());
			}

		    return batchDTO;
		}

	public List<BatchDTO> batchsToBatchDTOs(List<Batch> batchs) {
		return batchs.stream().map(this::batchToBatchDTO).collect(Collectors.toList());
	}

	public void updateBatchFromDto(@Valid BatchDTO batchDto, Batch existingBatch) {

		existingBatch.setId(batchDto.getId());
		existingBatch.setClassLink(batchDto.getClassLink());
		existingBatch.setLanguage(batchDto.getLanguage());
		existingBatch.setStartDate(batchDto.getStartDate());
		existingBatch.setEndDate(batchDto.getEndDate());
		existingBatch.setIsActive(batchDto.getIsActive());
	}

}
