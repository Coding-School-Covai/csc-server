package com.csc.project.batch.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.csc.project.batch.dto.StaffDTO;
import com.csc.project.batch.dto.StaffFilter;
import com.csc.project.batch.dto.StaffPageResponse;
import com.csc.project.batch.entity.Staff;
import com.csc.project.batch.jpa.spec.StaffSpecification;
import com.csc.project.batch.repository.StaffRepository;
import com.csc.project.batch.service.StaffService;
import com.csc.project.batch.service.mapper.StaffMapper;
import com.csc.project.common.exception.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class StaffServiceImpl implements StaffService{
	
	private final StaffRepository staffRepository;
	private final StaffMapper staffMapper;

    @Override
    public void addStaff(StaffDTO staffDto) {
        log.info("Adding staff with data: {}", staffDto);
        Staff staff = StaffMapper.staffDtoToStaff(staffDto);
        staffRepository.save(staff);
        log.info("Staff added successfully.");
    }


	@Override
	public StaffPageResponse getStaffs(StaffFilter staffFilter) {
		log.info(
				"Get all staffs with filter params: offset: {}, limit: {}, order: {}, orderby: {}, searchInput: {}",
				staffFilter.getOffset(), staffFilter.getLimit(), staffFilter.getOrder(),
				staffFilter.getOrderBy(), staffFilter.getSearchInput());
		Specification<Staff> specification = StaffSpecification.buildSpecification(staffFilter);

		Page<Staff> staffPage = staffRepository.findAll(specification,
				PageRequest.of(staffFilter.getOffset(), staffFilter.getLimit(),
						Sort.by(Sort.Direction.fromString(staffFilter.getOrder()), staffFilter.getOrderBy())));

		List<StaffDTO> staffDTOs = staffMapper.staffsToStaffDTOs(staffPage.getContent());
		log.info("Total affiliates found: {}", staffPage.getTotalElements());
		return new StaffPageResponse(staffPage.getTotalElements(), staffDTOs);
	}
	

	@Override
	public StaffDTO getStaffById(long staffId) {
		log.info("Get staff by id: {}", staffId);
		Staff staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
		return staffMapper.staffToStaffDTO(staff);
	}

	@Override
	public void updateStaff(long staffId, @Valid StaffDTO staffDto) {
		log.info("Update staff with data: {}", staffDto);
		Staff existingStaff = staffRepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + staffId));
		staffMapper.updateStaffFromDto(staffDto, existingStaff);
		Staff updatedStaff = staffRepository.save(existingStaff);
		log.info("Updated staff with id: {}", updatedStaff.getId());
	}

}
