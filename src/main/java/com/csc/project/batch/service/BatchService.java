package com.csc.project.batch.service;

import com.csc.project.batch.dto.BatchDTO;
import com.csc.project.batch.dto.BatchFilter;
import com.csc.project.batch.dto.BatchPageResponse;

import jakarta.validation.Valid;

public interface BatchService {

	void addBatch(BatchDTO batchDto);

	BatchPageResponse getBatchs(BatchFilter batchFilter);

	BatchDTO getBatchById(long batchId);

	void updateBatch(long batchId, @Valid BatchDTO batchDto);
}
