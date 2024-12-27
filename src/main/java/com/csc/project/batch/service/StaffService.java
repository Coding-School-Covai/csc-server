package com.csc.project.batch.service;

import com.csc.project.batch.dto.StaffDTO;
import com.csc.project.batch.dto.StaffFilter;
import com.csc.project.batch.dto.StaffPageResponse;

import jakarta.validation.Valid;

public interface StaffService {

	void addStaff(StaffDTO staffDto);

	StaffPageResponse getStaffs(StaffFilter staffFilter);

	StaffDTO getStaffById(long staffId);

	void updateStaff(long staffId, @Valid StaffDTO staffDto);
}
