package com.csc.project.batch.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.dto.BatchFilter;
import com.csc.project.batch.dto.BatchPageResponse;
import com.csc.project.batch.entity.Batch;
import com.csc.project.batch.entity.Course;
import com.csc.project.batch.entity.Slot;
import com.csc.project.batch.jpa.spec.BatchSpecification;
import com.csc.project.batch.repository.BatchRepository;
import com.csc.project.batch.repository.CourseRepository;
import com.csc.project.batch.repository.SlotRepository;
import com.csc.project.batch.service.BatchService;
import com.csc.project.batch.service.mapper.BatchMapper;
import com.csc.project.common.GoogleMeetService;
import com.csc.project.common.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BatchServiceImpl implements BatchService{
	
	private final BatchRepository batchRepository;
	private final BatchMapper batchMapper;
	private final SlotRepository slotRepository;
    private final GoogleMeetService googleMeetService;
    private final CourseRepository courseRepository;

    @Override
    public void addBatch(BatchDTO batchDto) {
        log.info("Adding batch with data: {}", batchDto);

        Course course = courseRepository.findById(batchDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + batchDto.getCourseId()));

        Slot slot = slotRepository.findById(batchDto.getSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Slot not found with ID: " + batchDto.getSlotId()));

        Batch batch = BatchMapper.batchDtoToBatch(batchDto);
        batch.setCourse(course);
        batch.setSlot(slot);

        batchRepository.save(batch);

        log.info("Batch added successfully.");
    }


	@Override
	public BatchPageResponse getBatchs(BatchFilter batchFilter) {
		log.info(
				"Get all batchs with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
				batchFilter.getOffset(), batchFilter.getLimit(), batchFilter.getOrder(),
				batchFilter.getOrderBy(), batchFilter.getSearchInput());
		Specification<Batch> specification = BatchSpecification.buildSpecification(batchFilter);

		Page<Batch> batchPage = batchRepository.findAll(specification,
				PageRequest.of(batchFilter.getOffset(), batchFilter.getLimit(),
						Sort.by(Sort.Direction.fromString(batchFilter.getOrder()), batchFilter.getOrderBy())));

		List<BatchDTO> batchDTOs = batchMapper.batchsToBatchDTOs(batchPage.getContent());
		log.info("Total affiliates found: {}", batchPage.getTotalElements());
		return new BatchPageResponse(batchPage.getTotalElements(), batchDTOs);
	}
	

	@Override
	public BatchDTO getBatchById(long batchId) {
		log.info("Get batch by id: {}", batchId);
		Batch batch = batchRepository.findById(batchId)
				.orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
		return batchMapper.batchToBatchDTO(batch);
	}

	@Override
	public void updateBatch(long batchId, @Valid BatchDTO batchDto) {
		log.info("Update batch with data: {}", batchDto);
		Batch existingBatch = batchRepository.findById(batchId)
				.orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
		batchMapper.updateBatchFromDto(batchDto, existingBatch);
		if(batchDto.getCourseId() != null) {
			Course newCourse = courseRepository.findById(batchDto.getCourseId())
					.orElseThrow(() -> new ResourceNotFoundException(
							String.format("Course not found with ID: %d", batchDto.getCourseId())));
			existingBatch.setCourse(newCourse);
			}
		if(batchDto.getSlotId() != null) {
		Slot newSlot = slotRepository.findById(batchDto.getSlotId())
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Slot not found with ID: %d", batchDto.getSlotId())));
		existingBatch.setSlot(newSlot);
		}
		Batch updatedBatch = batchRepository.save(existingBatch);
		log.info("Updated batch with id: {}", updatedBatch.getId());
	}

	@Override
    public String getClassLink(Long batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));

        if (batch.getClassLink() == null || batch.getClassLinkExpiry() == null ||
            batch.getClassLinkExpiry().isBefore(LocalDateTime.now())) {
            
            String newLink = googleMeetService.createMeetLink();
            batch.setClassLink(newLink);
            batch.setClassLinkExpiry(LocalDateTime.now().plusHours(1));
            batchRepository.save(batch);
        }
        return batch.getClassLink();
	}
}
