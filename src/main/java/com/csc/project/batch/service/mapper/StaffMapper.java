package com.csc.project.batch.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csc.project.batch.dto.StaffDTO;
import com.csc.project.batch.entity.Staff;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StaffMapper {

	public static Staff staffDtoToStaff(StaffDTO staffDto) {
	    if (staffDto == null) {
	        return null;
	    }

		Staff staff = new Staff();
		staff.setId(staffDto.getId());
		staff.setName(staffDto.getName());
		staff.setEmail(staffDto.getEmail());
		staff.setDoj(staffDto.getDoj());
		staff.setDob(staffDto.getDob());
		staff.setMobileNo(staffDto.getMobileNo());
		staff.setAddress1(staffDto.getAddress1());
		staff.setAddress2(staffDto.getAddress2());
		staff.setCity(staffDto.getCity());
		staff.setState(staffDto.getState());
		staff.setCountry(staffDto.getCountry());
		staff.setPincode(staffDto.getPincode());
		staff.setPermission(staffDto.getPermission());
		staff.setClassCount(staffDto.getClassCount());
		staff.setSalary(staffDto.getSalary());
		staff.setIdProofNumber(staffDto.getIdProofNumber());
		staff.setQualification(staffDto.getQualification());
		staff.setContractNumber(staffDto.getContractNumber());
		staff.setIsActive(staffDto.getIsActive());
		return staff;
	}

	public StaffDTO staffToStaffDTO(Staff staff) {
		    if (staff == null) {
		        return null;
		    }
		    
		    StaffDTO staffDTO = new StaffDTO();
		    staffDTO.setId(staff.getId());
		    staffDTO.setName(staff.getName());
		    staffDTO.setEmail(staff.getEmail());
		    staffDTO.setDoj(staff.getDoj());
		    staffDTO.setDob(staff.getDob());
		    staffDTO.setMobileNo(staff.getMobileNo());
		    staffDTO.setAddress1(staff.getAddress1());
		    staffDTO.setAddress2(staff.getAddress2());
		    staffDTO.setCity(staff.getCity());
		    staffDTO.setState(staff.getState());
		    staffDTO.setCountry(staff.getCountry());
		    staffDTO.setPincode(staff.getPincode());
		    staffDTO.setPermission(staff.getPermission());
		    staffDTO.setClassCount(staff.getClassCount());
		    staffDTO.setSalary(staff.getSalary());
		    staffDTO.setQualification(staff.getQualification());
		    staffDTO.setIdProofNumber(staff.getIdProofNumber());
		    staffDTO.setContractNumber(staff.getContractNumber());
		    staffDTO.setIsActive(staff.getIsActive());
		    return staffDTO;
		}

	public List<StaffDTO> staffsToStaffDTOs(List<Staff> staffs) {
		return staffs.stream().map(this::staffToStaffDTO).collect(Collectors.toList());
	}

	public void updateStaffFromDto(@Valid StaffDTO staffDto, Staff existingStaff) {

		existingStaff.setId(staffDto.getId());
		existingStaff.setName(staffDto.getName());
		existingStaff.setEmail(staffDto.getEmail());
		existingStaff.setDoj(staffDto.getDoj());
		existingStaff.setDob(staffDto.getDob());
		existingStaff.setMobileNo(staffDto.getMobileNo());
		existingStaff.setAddress1(staffDto.getAddress1());
		existingStaff.setAddress2(staffDto.getAddress2());
        existingStaff.setCity(staffDto.getCity());
        existingStaff.setState(staffDto.getState());
        existingStaff.setCountry(staffDto.getCountry());
        existingStaff.setPincode(staffDto.getPincode());
        existingStaff.setPermission(staffDto.getPermission());
        existingStaff.setClassCount(staffDto.getClassCount());
        existingStaff.setSalary(staffDto.getSalary());
        existingStaff.setQualification(staffDto.getQualification());
        existingStaff.setIdProofNumber(staffDto.getIdProofNumber());
        existingStaff.setContractNumber(staffDto.getContractNumber());
        existingStaff.setIsActive(staffDto.getIsActive());
	}

}
